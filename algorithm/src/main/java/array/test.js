const processRowUpdate = (newRow) => {
    const newUser = newRow.isNew ? {
        ...newRow,
        id: undefined
    } : newRow;

    UserService.saveUser(newUser).then(response => {
        console.log('User saved successfully:', response.data);
        const updatedRow = {...response.data, isNew: false};
        setRows(rows.map((row) => (row.id === newRow.id ? updatedRow : row)));
    }).catch(error => {
        console.error('Error saving user:', error);
    });
};


const processRowUpdate = useCallback(
    async (newRow) => {
        const newUser = newRow.isNew ? {
            ...newRow,
            id: undefined
        } : newRow;

        const response = await UserService.saveUser(newUser);
        console.log('User saved successfully:', response.data);
        const updatedRow = {...response.data, isNew: false};
        setRows(rows.map((row) => (row.id === newRow.id ? updatedRow : row)));
        return response;
    },
    [UserService.saveUser],
);
