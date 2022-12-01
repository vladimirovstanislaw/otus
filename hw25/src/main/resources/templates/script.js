function getUserById() {
    const userIdTextBox = document.getElementById('userIdTextBox');
    const userDataContainer = document.getElementById('userDataContainer');
    const id = userIdTextBox.value;
    //fetch('api/user?id=' + id)
    fetch('api/user/' + id)
        .then(response => response.json())
        .then(user => userDataContainer.innerHTML = JSON.stringify(user));
}

function getAllClients() {
    const tableId = document.getElementById('clientsData');
    tableId.innerHTML = "";

    fetch('api/user/all').then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        let tableData = '';
        for (let i = 0; i < data.length; i++) {
            tableData +=
                `<tr><td><span>${data[i].id}</span></td></tr>
                 <tr><td><span>${data[i].login}</span></td></tr>
                 <tr><td><span>${data[i].name}</span></td></tr>
                 <tr><td><span>${data[i].password}</span></td></tr>`;

        }

        tableId.innerHTML = tableData;
    }).catch(function () {
        console.log("SpookyBooo");
    });
}