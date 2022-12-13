function deleteCombtn() {

    $(".goDelCom").on("click", function () {
        $(".goDelCom").attr("cId", $(this).attr("cId"));
        com_id = $(this).attr("cId");
        deleteCom(com_id)
    })
    function deleteCom(com_id) {
        r = confirm('確定刪除?')
        if (r == true) {
            $.ajax({
                url: '/comment/delete/' + com_id,
                contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
                method: 'get',
                // dataType: 'json', // 回傳的資料型別
                success: function (result) {
                    alert(result);
                    $("#comment-box").empty();
                    getComm($("#bigCard").attr("pId"));
                },
                error: function (err) {
                    console.log(err);
                }
            })
        } else {

        }

    }

}