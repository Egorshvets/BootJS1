const url = 'http://localhost:8080/api/users'

    function getAllUsers() {

    fetch(url).then(res => res.json()).then(function (response) {

        let table = document.createElement('table');
        let thead = document.createElement('thead');
        let tbody = document.createElement('tbody');

        table.classList.add('table-striped', 'align-middle', 'auto__table', 'table')
        table.appendChild(thead);
        table.appendChild(tbody);
        document.getElementById('cardBody').innerHTML = ''

        document.getElementById('cardBody').appendChild(table);

        let row_1 = document.createElement('tr');
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = "Id";
        let heading_2 = document.createElement('th');
        heading_2.innerHTML = "UserName";
        let heading_3 = document.createElement('th');
        heading_3.innerHTML = "Email";
        let heading_4 = document.createElement('th');
        heading_4.innerHTML = "Age";
        let heading_5 = document.createElement('th');
        heading_5.innerHTML = "Roles";
        let heading_6 = document.createElement('th');
        heading_6.innerHTML = "Edit";
        let heading_7 = document.createElement('th');
        heading_7.innerHTML = "Delete";

        row_1.appendChild(heading_1);
        row_1.appendChild(heading_2);
        row_1.appendChild(heading_3);
        row_1.appendChild(heading_4);
        row_1.appendChild(heading_5);
        row_1.appendChild(heading_6);
        row_1.appendChild(heading_7);
        thead.appendChild(row_1);

        for(let user of response) {
            console.log(user.authorities.role)
            let role = user.authorities.role
            let row_2 = document.createElement('tr');
            let row_2_data_1 = document.createElement('td');
            row_2_data_1.innerHTML = user.id;
            let row_2_data_2 = document.createElement('td');
            row_2_data_2.innerHTML = user.username;
            let row_2_data_3 = document.createElement('td');
            console.log(user)
            row_2_data_3.innerHTML = user.email;
            let row_2_data_4 = document.createElement('td');
            row_2_data_4.innerHTML = user.age;
            let row_2_data_5 = document.createElement('td');
            row_2_data_5.innerHTML = user.stringRole;
            let row_2_data_6 = document.createElement('td');
            row_2_data_6.classList.add('text-center')
            let row_2_data_6_but = document.createElement('button');



            row_2_data_6_but.classList.add('btn-primary', 'btn')
            row_2_data_6_but.onclick = function() {edit(user.id)}
            row_2_data_6_but.innerHTML = "Edit";
            row_2_data_6.appendChild(row_2_data_6_but)

            let row_2_data_7 = document.createElement('td');
            row_2_data_7.classList.add('text-center')
            let row_2_data_7_but = document.createElement('button');
            row_2_data_7_but.onclick = function () {deleteSubmit(user.id)}
            row_2_data_7_but.classList.add('btn-danger', 'btn')
            row_2_data_7_but.innerHTML = "Delete";
            row_2_data_7.appendChild(row_2_data_7_but)

            row_2.appendChild(row_2_data_1);
            row_2.appendChild(row_2_data_2);
            row_2.appendChild(row_2_data_3);
            row_2.appendChild(row_2_data_4);
            row_2.appendChild(row_2_data_5);
            row_2.appendChild(row_2_data_6);
            row_2.appendChild(row_2_data_7);
            tbody.appendChild(row_2);
        }


    });
    }
    getAllUsers()

function edit(id) {
    const editHTML = document.getElementById('modal1');
    let tempModal = '';
    fetch('/api/users/' + id)
        .then(result => result.json())
        .then(user => {
            tempModal = '<div class="modal fade" id="edituser" tabIndex="-1" role="dialog" ' +
                'aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                '<div class="modal-dialog" role="document">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<h5 class="modal-title" id="exampleModalLabel">Edit user</h5>' +
                '<button type="button" class="close" data-dismiss="modal" ' +
                'aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>' +
                '<div class="modal-body">' +
                '<div class="container-fluid">' +
                '<div class="row">' +
                '<div class="col-sm-3">' +
                '</div>' +
                '<div class="col-sm-6 text-center">' +
                '<form method="PATCH">' +
                '<div class="form-group">' +
                '<label for="edit1">' +
                '<b>' +
                'ID' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit1" name="id" value="' + user.id + '" readonly>' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit2">' +
                '<b>' +
                'UserName' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'value="' + user.username + '" ' +
                'class="form-control" id="edit2" name="firstName" ' +
                'placeholder="First name">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit3">' +
                '<b>' +
                'Email' +
                '</b>' +
                '</label>' +
                '<input type="text" ' +
                'class="form-control" id="edit3" value="' + user.email + '" name="lastName" ' +
                'placeholder="Last name">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit4">' +
                '<b>' +
                'Age' +
                '</b>' +
                '</label>' +
                '<input type="number" ' +
                'class="form-control" id="edit4" value="' + user.age + '" name="age" ' +
                'placeholder="Age">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="edit5">' +
                '<b>' +
                'Password' +
                '</b>' +
                '</label>' +
                '<input type="password" class="form-control" name="password" ' +
                'id="edit5" value="">' +
                '<input type="hidden" name="password1" id="passhid" value="' + user.password + '">' +
                '</div>' +

                '<div class="form-group">' +
                '<label for="edit6">' +
                '<b>' +
                'Role' +
                '</b>' +
                '</label>' +
                '<select multiple class="form-control" size="2" ' +
                'name="roles" id="edit6">' +
                '<option value="ADMIN">ADMIN</option>' +
                '<option value="USER">USER</option>' +
                '</select>' +
                '</div>' +
                '</form>' +

                '</div>' +
                '<div class="col-sm-3">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary" ' +
                'data-dismiss="modal">Close' +
                '</button>' +
                '<button type="submit" class="btn btn-primary" onclick="editSubmit()" data-dismiss="modal">Edit</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';

            editHTML.innerHTML = tempModal;
            $("#edituser").modal();
        });

}
function addNewUser() {
    let userName = document.getElementById('add1').value
    let email = document.getElementById('add2').value
    let age = document.getElementById('add3').value
    let password = document.getElementById('add4').value
    let roles = document.getElementById('add5').value

    for (let i = 0; i < roles.length; i++) {
        if (roles === 'ADMIN') {
            roles = {
                'role': 'ADMIN',
                'authority': 'ADMIN'
            }
            console.log('11111111111111111111111111111111111111111111')
        }
        if (roles === 'USER') {
            roles = {
                'role': 'USER',
                'authority': 'USER'
            }
            console.log(roles)
            console.log('2222222222222222222222222222222222222222222')
        }
    }

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            userName:userName,
            age:age,
            email:email,
            password:password,
            authorities:[roles]
        })
    })
        .then(() => {
            document.getElementById('home-tab').click()
            getAllUsers()
            document.newUser.reset()
        })
   let bod =  JSON.stringify({
        userName:userName,
        age:age,
        email:email,
        password:password,
        authorities:roles
    })
    console.log(bod)

}
function deleteSubmit(id) {
    fetch('/api/users/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
        .then(() => {
            getAllUsers()
        })
}

function editSubmit() {
    let id = document.getElementById('edit1').value
    let userName = document.getElementById('edit2').value
    let email = document.getElementById('edit3').value
    let age = document.getElementById('edit4').value
    let password = document.getElementById('edit5').value
    let passwordHid = document.getElementById('passhid').value
    let roles = document.getElementById('edit6').value



    if (password === '') {
        password = passwordHid
    }

    for (let i = 0; i < roles.length; i++) {
        if (roles === 'ADMIN') {
            roles = {
                'role': 'ADMIN',
                'authority': 'ADMIN'
            }
        }
        if (roles === 'USER') {
            roles = {
                'role': 'USER',
                'authority': 'USER'
            }
        }
    }

    fetch('/api/users', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'id':id,
            userName:userName,
            age:age,
            email:email,
            password:password,
            authorities:[roles]
        })
    })
        .then(() => {
            getAllUsers()
        })
}
