import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystem {
    public static void main(String... strings) {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        fileSystem.mkdir("/a/b/c/e");
        fileSystem.mkdir("/a/b/c/f");
        fileSystem.addContentToFile("/a/b/c/g", "ggg");
        fileSystem.addContentToFile("/a/b/c/d","hello");
        fileSystem.addContentToFile("/a/b/c/d","world");
//        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.ls("/a/b/c"));
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        System.out.println(fileSystem.readContentFromFile("/a/b/c/g"));
    }

    private Directory root = new Directory("/");

    public List<String> ls(String path) {
        if("/".equals(path)) {
            return root.ls();
        }

        String parentPath = path.substring(0, path.lastIndexOf("/"));
        String name = path.substring(path.lastIndexOf("/") + 1);
        Directory parentDirectory = findDirectory(parentPath);
        if (Objects.isNull(parentDirectory)) {
            return Collections.emptyList();
        }

        BaseDirectory item = parentDirectory.findChild(name);

        return Objects.nonNull(item) ? item.ls() : Collections.emptyList();
    }

    public void mkdir(String path) {
        String[] parts = splitIgnoreEmpty(path);
        Directory current = root;
        int i = 0;
        while (i < parts.length && current != null) {
            String part = parts[i++];
            BaseDirectory foundChild = current.findChild(part);
            if (Objects.isNull(foundChild)) {
                Directory directory = new Directory(part);
                current.addChild(directory);
                current = directory;
            } else if (isDirectory(foundChild)) {
                current = (Directory) foundChild;
            } else {
                throw new RuntimeException("File found");
            }
        }
    }

    public void addContentToFile(String path, String fileContent) {
        String parentPath = path.substring(0, path.lastIndexOf("/"));
        String name = path.substring(path.lastIndexOf("/") + 1);
        Directory parentDirectory = findDirectory(parentPath);
        if (Objects.isNull(parentDirectory)) {
            return;
        }

        BaseDirectory foundChild = parentDirectory.findChild(name);
        if(isDirectory(foundChild)) {
            return;
        }

        File foundFile = Objects.isNull(foundChild) ? new File(name) : (File) foundChild;
        foundFile.addContent(fileContent);
        parentDirectory.addChild(foundFile);
    }

    public String readContentFromFile(String path) {
        String parentPath = path.substring(0, path.lastIndexOf("/"));
        String name = path.substring(path.lastIndexOf("/") + 1);
        Directory parentDirectory = findDirectory(parentPath);
        if (Objects.isNull(parentDirectory)) {
            return "";
        }

        BaseDirectory foundChild = parentDirectory.findChild(name);
        if(Objects.nonNull(foundChild) && !isDirectory(foundChild)) {
            return ((File)foundChild).readContent();
        }

        return "";
    }

    private Directory findDirectory(String path) {
        String[] parts = splitIgnoreEmpty(path);
        Directory current = root;
        int i = 0;
        while (i < parts.length && current != null) {
            String part = parts[i++];
            BaseDirectory foundChild = current.findChild(part);
            if (Objects.nonNull(foundChild) && isDirectory(foundChild)) {
                current = (Directory) foundChild;
            } else {
                current = null;
            }
        }

        return current;
    }

    private boolean isDirectory(BaseDirectory baseDirectory) {
        return baseDirectory instanceof Directory;
    }

    private String[] splitIgnoreEmpty(String path) {
        return Stream.of(path.split("/")).filter(str -> !str.isEmpty()).toArray(String[]::new);
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    private abstract class BaseDirectory {
        @EqualsAndHashCode.Include
        protected String name;

        protected abstract List<String> ls();
    }

    @Getter
    private class Directory extends BaseDirectory {
        private Set<BaseDirectory> children = new HashSet<>();

        public Directory(String name) {
            super(name);
        }

        @Override
        protected List<String> ls() {
            //TODO: sort lexicographic order
            return children.stream().map(BaseDirectory::getName).sorted().collect(Collectors.toList());
        }

        public void addChild(BaseDirectory child) {
            children.add(child);
        }

        public BaseDirectory findChild(String name) {
            return getChildren().stream()
                    .filter(child -> name.equals(child.getName())).findFirst().orElse(null);
        }
    }

    private class File extends BaseDirectory {
        private StringBuilder contentBuilder = new StringBuilder();

        public File(String name) {
            super(name);
        }

        @Override
        protected List<String> ls() {
            return Collections.singletonList(getName());
        }

        public void addContent(String content) {
            contentBuilder.append(content);
        }

        public String readContent() {
            return contentBuilder.toString();
        }
    }


}
