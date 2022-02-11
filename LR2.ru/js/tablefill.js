function readTextFile(file) {
    let rawFile = new XMLHttpRequest();
    rawFile.open("GET", file, false);
    
    let result;
    
    rawFile.onreadystatechange = function ()
    {
        if(rawFile.readyState === 4)
        {
            if(rawFile.status === 200 || rawFile.status == 0)
            {
                result = rawFile.responseText;
            }
        }
    }
    rawFile.send();
    
    return result;
}

table = document.getElementById("phonetable");

tableBody = table.tBodies[0];

tableRow = tableBody.insertRow();
tableRow.id = 'header';

let rawData = readTextFile("data/table.xml");

let parser = new DOMParser();

xml = parser.parseFromString(rawData, 'text/xml');

let tables = xml.getElementsByTagName("phone");

for (let i = 0; i < tables.length; i++) {
    tableRow = tableBody.insertRow();
    tableRow.id = 'iteration_' + i;

    reviews = tables[i].getElementsByTagName("review");

    tableCell = tableRow.insertCell();
    tableCell.id = 0;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.textContent = tables[i].getAttribute("modelindex");
    
    tableCell = tableRow.insertCell();
    tableCell.id = 1;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.textContent = tables[i].getAttribute("name");
    
    tableCell = tableRow.insertCell();
    tableCell.id = 2;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.textContent = tables[i].getAttribute("manufacturer");
    
    tableCell = tableRow.insertCell();
    tableCell.id = 3;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.textContent = tables[i].getAttribute("operatingsystem");
    
    tableCell = tableRow.insertCell();
    tableCell.id = 4;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.textContent = tables[i].getAttribute("releaseyear");

    tableCell = tableRow.insertCell();
    tableCell.id = `${5}_${0}`;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);

    if (reviews.length > 0) {
        tableCell.textContent = reviews[0].getAttribute("website");
    }

    tableCell = tableRow.insertCell();
    tableCell.id = `${6}_${0}`;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);

    if (reviews.length > 0) {
        tableCell.textContent = reviews[0].getAttribute("score");
    }

    tableCell = tableRow.insertCell();
    tableCell.id = 7;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("contenteditable", true);
    tableCell.setAttribute("rowspan", reviews.length);

    average = tables[i].getElementsByTagName("average")[0];

    if (average) {
        tableCell.textContent  = average.textContent;
    }

    tableCell = tableRow.insertCell();
    tableCell.id = 8;
    tableCell.setAttribute("class", "table-cell");
    tableCell.setAttribute("rowspan", reviews.length);
    tableCell.innerHTML = "<input class=\"table-checkbox\" type=\"checkbox\">";

    for (let j = 1; j < reviews.length; j++) {
        tableRow = tableBody.insertRow();
        tableRow.id = 'iteration_' + i + 'sub' + j;

        tableCell = tableRow.insertCell();
        tableCell.id = `${5}_${j}`;
        tableCell.setAttribute("class", "table-cell");
        tableCell.setAttribute("contenteditable", true);
        tableCell.textContent = reviews[j].getAttribute("website");

        tableCell = tableRow.insertCell();
        tableCell.id = `${6}_${j}`;
        tableCell.setAttribute("class", "table-cell");
        tableCell.setAttribute("contenteditable", true);
        tableCell.textContent = reviews[j].getAttribute("score");
    }
}
