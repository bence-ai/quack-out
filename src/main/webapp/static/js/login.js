buttonLoginRegister();

function buttonLoginRegister() {
    let loginButton = document.getElementById('loginButton');
    let registerButton = document.getElementById('registerButton');


    let modalRegister = document.getElementById('registerModal');
    console.dir(modalRegister)


    console.dir(loginButton);
    console.dir(registerButton)
    loginButton.onclick = function () {
        loginModalsPrinter('login');
        console.log("onclick")
    }
    registerButton.onclick = function () {
        loginModalsPrinter('register');
        console.log("onclick")

    }
}

function loginModalsPrinter(modal) {
    let modalRegister = document.getElementById('registerModal');
    let modalLogin = document.getElementById('loginModal');

    let spanClose = document.getElementsByClassName("close")[0];
    let spanClose1 = document.getElementsByClassName("close")[1];

    if (modal === 'register') {
        modalRegister.style.display = 'block';
        spanClose.onclick = function () {
            modalRegister.style.display = 'none';
        }
        window.onclick = function (event) {
            if (event.target === modalRegister) {
                modalRegister.style.display = 'none';
            }
        };
    } else if(modal === 'login') {
        modalLogin.style.display = 'block';
        spanClose1.onclick = function () {
            modalLogin.style.display = 'none';
        }
        window.onclick = function (event) {
            if (event.target === modalLogin) {
                modalLogin.style.display = 'none';
            }
        };
    }
}