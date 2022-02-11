function tableDeleteButtonOnClick() {
    tableBody = document.getElementById("phonetable").tBodies[0];
    
    checkboxes = tableBody.getElementsByClassName("table-checkbox");
    
    let toDeleteAmount = 0;
    
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            toDeleteAmount++;
        }
    }
    
    if (checkboxes.length > 0) {
        if (confirm(`Нажмите \"Ок\" чтобы удалить ${toDeleteAmount} элементов из таблицы или отмена чтобы отменить`)) {
            for (let i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    tableBody.deleteRow(i);
                    i--;
                }
            }
        }
    }
}

function tableAddNewRow() {
    table = document.getElementById("phonetable");

    tableBody = table.tBodies[0];

    tableRow = tableBody.insertRow(table.rows.length - 2);

    tableRow.id = tableBody.rows.length - 2;

    tableCell = tableRow.insertCell();
    tableCell.id = 0;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" type="hidden" name="number" required class="table-cell-input" value="${document.getElementById("table-cell-input-number").value}">${document.getElementById("table-cell-input-number").value}`;

    tableCell = tableRow.insertCell();
    tableCell.id = 1;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="name" required class="table-cell-input" value="${document.getElementById("table-cell-input-name").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 2;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="manufacturer" required class="table-cell-input" value="${document.getElementById("table-cell-input-manufacturer").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 3;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="operatingsystem" required class="table-cell-input" value="${document.getElementById("table-cell-input-operatingsystem").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 4;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="releaseyear" required class="table-cell-input" value="${document.getElementById("table-cell-input-releaseyear").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 5;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="site" required class="table-cell-input" value="${document.getElementById("table-cell-input-site").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 6;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" name="score" required class="table-cell-input" value="${document.getElementById("table-cell-input-score").value}">`;

    tableCell = tableRow.insertCell();
    tableCell.id = 7;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = `<input form="sidebar-form-save" class="table-checkbox" name="checkbox" type="checkbox" value="${tableRow.id}">`;

    document.getElementById("table-cell-input-number").value = "";
    document.getElementById("table-cell-input-name").value = "";
    document.getElementById("table-cell-input-manufacturer").value = "";
    document.getElementById("table-cell-input-operatingsystem").value = "";
    document.getElementById("table-cell-input-releaseyear").value = "";
    document.getElementById("table-cell-input-site").value = "";
    document.getElementById("table-cell-input-score").value = "";

    return false;
}
