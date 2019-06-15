function toggle_visibility(id) {
    var x = document.getElementById(id);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function submitFormCheckout() {
    var form = document.getElementById('submit-form-button')
    form.submit();
}

var app = window.app || {},
    business_paypal = ''; // aquí va tu correo electrónico de paypal

(function ($) {
    'use strict';

    //no coflict con underscores

    app.init = function () {
        //totalItems totalAmount
        var total = 0,
            items = 0
        try {
            var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []};



        if (undefined != cart.items && cart.items != null && cart.items != '' && cart.items.length > 0) {
            _.forEach(cart.items, function (n, key) {
                items = (items + n.quantity)
                total = total + (n.quantity * n.price)
            });

        }

        $('#totalItems').text(items)
        $('.totalAmount').text('$ ' + total + ' USD')
        }
        catch (e) {
            localStorage.setItem('cart',null);
            alert(e)
        }

    }

    app.createProducts = function () {

        let wrapper = $('.productosWrapper'),
            contenido = ''

        for (var i = 0; i < productos.length; i++) {

            contenido += '<div class="coin-wrapper" >'
            contenido += '<a href="/productdetails/' + productos[i].id + '" ><img style="height: 300px" src="/photos/' + productos[i].pictureUrl + '" alt="' + productos[i].name + '"></a>'
            contenido += '		<span class="large-12 columns product-details">'
            contenido += '			<h3>' + productos[i].name + ' <span class="price"><br>$ ' + productos[i].price + ' USD</span></h3>'
            contenido += '		</span>'
            contenido += '		<a class="large-12 columns btn submit ladda-button prod-' + productos[i].id + '" data-style="slide-right" onclick="app.addtoCart(' + productos[i].id + ');">Add to cart</a>'
            contenido += '		<div class="clearfix"></div>'
            contenido += '</div>'

        }

        wrapper.html(contenido)

        localStorage.setItem('productos', JSON.stringify(productos))
    }

    app.addtoCart = function (id) {
        if (id === undefined) {
            alert("pi is null")
        }
        var l = Ladda.create(document.querySelector('.prod-' + id));
        alert("clicked")
        l.start();
        var productos = JSON.parse(localStorage.getItem('productos')),
            producto = _.find(productos, {'id': id}),
            quantity = 1

        if (undefined != producto) {
            if (quantity > 0) {
                setTimeout(
                    function () {
                        var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []};
                        app.searchProd(cart, producto.id, parseInt(quantity), producto.name, producto.price, producto.pictureUrl)
                        l.stop();
                    }, 500)
            } else {
                alert('Solo se permiten cantidades mayores a cero')
            }
        }
        else {
            alert('Oops! something went wrong ind' + id)
        }


    }

    app.searchProd = function (cart, id, quantity, name, price, pictureUrl, available) {
        var curProd = _.find(cart.items, {'id': id})

        if (undefined != curProd && curProd != null) {

            curProd.quantity = parseInt(curProd.quantity + quantity)


        }
        else {
            //sino existe lo agregamos al carrito
            var prod = {
                id: id,
                quantity: quantity,
                name: name,
                price: price,
                pictureUrl: pictureUrl,
                available: available
            }
            cart.items.push(prod)

        }
        localStorage.setItem('cart', JSON.stringify(cart))
        app.init()
        app.getProducts()
        app.updatePayForm()
    }

    app.getProducts = function () {
        try {


            var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []},
                msg = '',
                wrapper = $('.cart'),
                total = 0
            wrapper.html('')

            if (undefined == cart || null == cart || cart == '' || cart.items.length == 0) {
                wrapper.html('<li>Your cart is empty</li>');
                $('.cart').css('left', '-400%')
            } else {
                var items = '';
                _.forEach(cart.items, function (n, key) {

                    total = total + (n.quantity * n.price)
                    items += '<li>'
                    //'<a><img src="/photos/'+n.pictureUrl+'" /></a>'
                    //            contenido+= '<a href="/productdetails/'+productos[i].id+'" ><img src="/photos/'+productos[i].pictureUrl+'" alt="'+productos[i].name+'"></a>'
                    items += '<a href="/productdetails/' + n.id + '" ><img src="/photos/' + n.pictureUrl + '" alt="' + n.name + '"></a>'
                    items += '<h3 class="title">' + n.name + '<br><span class="price">' + n.quantity + ' x $ ' + n.price + ' USD</span> <button class="add" onclick="app.updateItem(' + n.id + ',' + n.available + ')"><i class="icon ion-minus-round"></i></button> <button onclick="app.deleteProd(' + n.id + ')" ><i class="icon ion-close-circled"></i></button><div class="clearfix"></div></h3>'
                    items += '</li>'
                });

                //agregar el total al carrito
                items += '<li id="total">Total : $ ' + total + ' USD <div id="submitForm"></div></li>'
                wrapper.html(items)
                $('.cart').css('left', '-500%')
            }
        }
        catch (e) {
            alert(e+2)
            localStorage.setItem('cart',null);
            
        }
    }

    app.updateItem = function (id, available) {

        var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []},
            curProd = _.find(cart.items, {'id': id})
        //actualizar el carrito
        curProd.quantity = curProd.quantity - 1;

        if (curProd.quantity > 0) {
            localStorage.setItem('cart', JSON.stringify(cart))
            app.init()
            app.getProducts()
            app.updatePayForm()
        } else {
            app.deleteProd(id, true)
        }
    }

    app.delete = function (id) {
        var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []};
        var curProd = _.find(cart.items, {'id': id})
        _.remove(cart.items, curProd);
        localStorage.setItem('cart', JSON.stringify(cart))
        app.init()
        app.getProducts()
        app.updatePayForm()
    }

    app.deleteProd = function (id, remove) {
        if (undefined != id && id > 0) {

            if (remove == true) {
                app.delete(id)
            } else {
                var conf = confirm('Do you want to delete this product?')
                if (conf) {
                    app.delete(id)
                }
            }

        }
    }

    app.updatePayForm = function () {

        var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []};
        var statics = '<form action="/product" method="post" ><input name="cart2" value="a" id="cart2" type="hidden">',
            dinamic = '',
            wrapper = $('#submitForm')

        wrapper.html('')

        if (undefined != cart && null != cart && cart != '') {
            var i = 1;
            _.forEach(cart.items, function (prod, key) {
                dinamic += '<input type="hidden" name="item_name_' + i + '" value="' + prod.name + '">'
                dinamic += '<input type="hidden" name="amount_' + i + '" value="' + prod.price + '">'
                dinamic += '<input type="hidden" name="item_number_' + i + '" value="' + prod.id + '" />'
                dinamic += '<input type="hidden" name="quantity_' + i + '" value="' + prod.quantity + '" />'
                i++;
            })

            statics += dinamic + '<button  type="submit" onclick="showcart3()" class="pay">Submit &nbsp;<i class="ion-chevron-right"></i></button></form>'

            wrapper.html(statics)
        }
    }

    $(document).ready(function () {
        app.init()
        app.getProducts()
        app.updatePayForm()
        app.createProducts()
    })

})(jQuery)

function showcart3() {

    var cart = (JSON.parse(localStorage.getItem('cart')) != null) ? JSON.parse(localStorage.getItem('cart')) : {items: []};
    var obj = JSON.stringify(cart.items)

    document.getElementById('cart2').value = obj;
    // localStorage.clear();
}