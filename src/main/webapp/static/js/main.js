let addCartButtons = document.querySelectorAll(".add-cart");
let cartCounter = document.querySelector(".cart-numbers")
let elemento = document.querySelector(".dropdown-content")
cartCounter.addEventListener("mouseover", () => {
    getCart()
    elemento.style.display="block";
} )
cartCounter.addEventListener("mouseout",() => {
    elemento.style.display="none";
} )
initButtons();



function initButtons () {
    if (document.querySelector(".cart-numbers").innerText != "0") {
        document.querySelector(".cart-numbers").style.display = "block";
    }
    for (let button of addCartButtons) {
        button.addEventListener("click", e => {
            let product_id = e.target.dataset.product_id;
            console.log(product_id);
            addToCart(product_id);
            getCart()
        })
    }
}

function addToCart(id) {
    fetch(`cart/add/?id=${id}`,{
        method: "GET"
    })
        .then(cartItems => cartItems.json())
        .then(response =>updateCart(response))
}

function getCart() {
    fetch("cart/get")
        .then(cartItems => cartItems.json())
        .then(elements => updateCartElements(elements))
}


function updateCart(number) {
    document.querySelector(".cart-numbers").innerHTML = JSON.parse(number).size;
    document.querySelector(".cart-numbers").style.display = "block";
}

function updateCartElements(elements) {
    let innerText = "";
    for (let product of elements) {
        console.log(product)
        let element = JSON.parse(product);
        innerText += "<div> <img class=\"small-image\" alt src=\"/static/img/" + element['imageName'] + "\"/> <p> " + element['name'] + " : " + element['defaultPrice'] + " &dollar;" + "</p> </div> <br>"
    }
    elemento.innerHTML = innerText;
    }


