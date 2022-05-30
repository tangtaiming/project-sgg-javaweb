/*
 * 
 *
 * @author tangtaiming
 * @version V0.0.1(2022/5/30 16:25)
 *
 *===============================================================================
 *
 */
window.onload = function() {
    updateTotal();

    var fruitTbl = document.getElementById("tbl_fruit");
    var rows = fruitTbl.rows;
    for (var x = 0; x < rows.length - 1; x++) {
        var tr = rows[x];
        //1.绑定鼠标悬浮以及离开时设置背景颜色事件
        tr.onmouseover = showBGColor;
        tr.onmouseout = clearBGColor;
        var cells = tr.cells;
        var priceTd = cells[1];
        //2.绑定鼠标悬浮在单价单元格变手势的事件
        priceTd.onmouseover = showHand;
        //
        priceTd.onclick = editPrice;

        var img = cells[4].firstChild;
        if (img && img.tagName == "IMG") {
            img.onclick = delFruit;
        }
    }
}

function delFruit() {
    if (event && event.srcElement && event.srcElement.tagName == "IMG") {
        if (window.confirm("是否确认删除当前库存记录?")) {
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTbl = document.getElementById("tbl_fruit");
            fruitTbl.deleteRow(tr.rowIndex);

            updateTotal();
        }
    }
}

function updateTotal() {
    var fruitTbl = document.getElementById("tbl_fruit");
    var rows = fruitTbl.rows
    var sum = 0;
    for (var x = 1; x < rows.length - 1; x++) {
        var tr = rows[x];
        var subtotal = parseInt(tr.cells[3].innerText);
        sum = sum + subtotal;
    }
    rows[rows.length - 1].cells[1].innerText = sum;
}

function updateSubtotal(element) {
    if (element && element.tagName == "TR") {
        var tds = element.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;
        var subtotal = parseInt(price) * parseInt(count);
        tds[3].innerText = subtotal;

        updateTotal();
    }

}

function updatePrice() {
    if (event && event.srcElement && event.srcElement.tagName == "INPUT") {
        var input = event.srcElement;
        var newPrice = input.value;
        var priceTd = input.parentElement;
        priceTd.innerText = newPrice;

        updateSubtotal(priceTd.parentElement);
    }
}

function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var priceTd = event.srcElement;
        if (priceTd.firstChild && priceTd.firstChild.nodeType==3) {
            var oldPrice = priceTd.innerText;
            priceTd.innerHTML = "<input type='text' size='4' />";
            var input = priceTd.firstChild;
            if (input.tagName == "INPUT") {
                input.value = oldPrice;
                input.select();
                input.onblur = updatePrice;

            }
        }
    }
}

function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        td.style.cursor = "pointer";
    }
}

function showBGColor() {
    //event : 当前发生的事件
    //event.srcElement : 事件源
    //alert(event.srcElement);
    //alert(event.srcElement.tagName);	--> TD
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "navy";

        var tds = tr.cells;
        for (var x = 0; x < tds.length; x++) {
            tds[x].style.color = "white";
            //先找到对应的这个元素
            if ((x + 1) == tds.length) {
                tds[x].children[0].src = "imgs/delWhite.png";
            }

        }
    }
}

function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "transparent";
        var tds = tr.cells;
        for (var x = 0; x < tds.length; x++) {
            tds[x].style.color = 'threeddarkshadow';
            if ((x + 1) == tds.length) {
                tds[x].children[0].src = "imgs/del.png";
            }
        }
    }
}