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
                    tableBody.deleteRow(i + 1);
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
    
    tableRow.id = 'iteration_' + tableBody.rows.length - 1;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 0;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-number").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 1;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-name").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 2;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-manufacturer").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 3;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-operationsystem").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 4;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-releasedate").value;

    tableCell = tableRow.insertCell();
    tableCell.id = `${5}_${0}`;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-review-website").value;

    tableCell = tableRow.insertCell();
    tableCell.id = `${6}_${0}`;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-review-score").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 7;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.textContent = document.getElementById("table-cell-input-score").value;
    
    tableCell = tableRow.insertCell();
    tableCell.id = 8;
    tableCell.setAttribute("class", "table-cell");
    tableCell.innerHTML = "<input class=\"table-checkbox\" type=\"checkbox\">";
    
    return false;
}