<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="psuti.kirgizov.phone.*, psuti.kirgizov.service.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//RU" "http://www.w3.org/TR/html4/strict.dtd">

<jsp:useBean id="service" class="psuti.kirgizov.service.PhoneService"/>
<c:set var="phoneList" value="${service.service(pageContext.request)}"/>

<html>
    <head>
        <title>Телефоны</title>
        <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/css/style.css">
        <script src="js\tableevents.js" charset="utf-8"></script>
        <script src="js\sidebarformevents.js" charset="utf-8"></script>
    </head>
    <body class="background-layout">
        <div class="page-layout">
            <div class="sidebar-layout">
                <div class="main-page">
                    <div class="table">
                        <div class="table-body">
                            <table id="phonetable" align="center" cols="9" cellpadding="4">
                                <thead>
									<tr>
										<th class="table-head-cell">Номер модели</th>
										<th class="table-head-cell">Название</th>
										<th class="table-head-cell">Производитель</th>
										<th class="table-head-cell">Операционная система</th>
										<th class="table-head-cell">Год выпуска</th>
                                        <th class="table-head-cell">Сайт</th>
                                        <th class="table-head-cell">Оценка</th>
										<th class="table-head-cell">Удалить</th>
									</tr>
								</thead>
								<tbody>
								    <c:forEach var="phone" items="${phoneList}">
								        <tr>
								            <td class="table-cell"><input form="sidebar-form-save" type="hidden" name="number" required class="table-cell-input" value="${phone.number}">${phone.number}</td>
								            <td class="table-cell"><input form="sidebar-form-save" name="name" required class="table-cell-input" value="${phone.name}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="manufacturer" required class="table-cell-input" value="${phone.manufacturer}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="operatingsystem" required class="table-cell-input" value="${phone.operatingSystem}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="releaseyear" required class="table-cell-input" value="${phone.releaseYear}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="site" required class="table-cell-input" value="${phone.site}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="score" required class="table-cell-input" value="${phone.score}"></td>
								            <td class="table-cell"><input form="sidebar-form-save" name="checkbox" class="table-checkbox" type="checkbox" value="${phone.number}"></td>
								        </tr>
								    </c:forEach>
									<tr>
										<form id="editphonetable" method="post" onsubmit="return tableAddNewRow()">
											<td class="table-cell"><input name="number" required id="table-cell-input-number" class="table-cell-input-new"></td>
											<td class="table-cell"><input name="name" required id="table-cell-input-name" class="table-cell-input-new"></td>
											<td class="table-cell"><input name="manufacturer" required id="table-cell-input-manufacturer" class="table-cell-input-new"></td>
											<td class="table-cell"><input name="operatingsystem" required id="table-cell-input-operatingsystem" class="table-cell-input-new"></td>
											<td class="table-cell"><input name="releaseyear" required id="table-cell-input-releaseyear" class="table-cell-input-new"></td>
                                            <td class="table-cell"><input name="site" id="table-cell-input-site" required class="table-cell-input-new"></td>
											<td class="table-cell"><input name="score" required id="table-cell-input-score" class="table-cell-input-new"></td>
											<td class="table-cell"><input name="add" type="submit" value="Создать" required class="table-cell-button"></button></td>
										</form>
									</tr>
								</tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <aside class="sidebar">
                    <div class="sidebar-form">
                        <form id="sidebar-form-model" method="post" onsubmit="return isValidFormNumber()">
                            <div class="sidebar-form-group">
                                <p class="sidebar-form-label">Введите номер модели:</p>
                                <input required class="sidebar-form-input" id="sidebar-form-input-number" name="number">
                                <input type="submit" value="Подтвердить" class="sidebar-form-button" id="sidebar-form-input-number-button" name="findByNumber"></button>
                            </div>
                        </form>
                        <form id="sidebar-form-name" method="post" onsubmit="return isValidFormName()">
                            <div class="sidebar-form-group">
                                <p class="sidebar-form-label">Введите название телефона:</p>
                                <input required class="sidebar-form-input" id="sidebar-form-input-name" type="text" name="name" value="Nokia 6131" onfocus="sidebarFormInputNameOnFocus()" onchange="sidebarFormInputNameOnChange()" onblur="sidebarFormInputNameOnBlur()">
                                <input type="submit" value="Подтвердить" class="sidebar-form-button" id="sidebar-form-input-name-button" name="findByName"></input>
                            </div>
                        </form>
                        <div>
                            <form id="sidebar-form-full" method="post">
                                <div class="sidebar-form-group-fulltable">
                                    <input type="submit" value="Показать всё" class="sidebar-form-button-fulltable" id="sidebar-form-button-fulltable" name="showFullTable"></input>
                                </div>
                            </form>
                            <form id="sidebar-form-save" method="post">
                                <div class="sidebar-form-group-savetable">
                                    <input type="hidden" name="editTable" value="true"/>
                                    <input type="submit" value="Применить изменения" class="sidebar-form-button-fulltable" id="sidebar-form-button-save" name="saveTable"></input>
                                </div>
                            </form>
                            <form id="sidebar-form-reset" method="post">
                                <div class="sidebar-form-group-fulltable">
                                    <input type="hidden" name="resetTable" value="true"/>
                                    <input type="submit" value="Сбросить" class="sidebar-form-button-fulltable" id="sidebar-form-button-reset" name="resetTable"></input>
                                </div>
                            </form>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    </body>
</html>
