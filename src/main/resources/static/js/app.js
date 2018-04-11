"use strict";
$(document).ready(function () {
    $("#sort").click(function () {
        var params = {};
        params.unsortedData = $("#unsortedInput").val();
        $.ajax({
            type: 'POST',
            url: contextPath + "sort",
            data: params,
            dataType: 'json',
            success: function (data) {
                $("#sortedOutput").val("Sorted Data: " + data.sortedData + "\nNumber of change of positions: " + data.changeOfPositions);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
});