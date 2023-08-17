$(document).ready(function() {
    let trLength =  $("#noOption").length ? 0 : $("#options tr").length;
    //옵션 추가 버튼
    $("#addOption").on("click", function(e){
        if($("#noOption").length) $("#noOption").remove();
        let text = ``;
        text += `  <tr>
                        <td><input type="text" name="lunchBoxOptions[${trLength}].lunchOptionTitle" /></td>
                        <td><input type="text" name="lunchBoxOptions[${trLength}].lunchOptionPrice" /></td>
                        <td colspan="2">
                            <a href="#" class="deleteOption">삭제</a>
                        </td>
                    </tr>`

        $("#options").append(text);
        trLength++;
        return false;

    })
    
    //옵션 추가 후 삭제 (DB데이터 삭제 x 단순히 화면상 옵션 row 삭제)
    $(document).on("click", ".deleteOption", function(e){
        if(confirm('정말로 삭제하시겠습니까?'))$(e.target).closest("tr").remove(); // 해당 버튼의 상위 <tr> 요소 삭제
    });

    
    //뒤로 가기
    $(".cancel").on("click", function() {
        // 페이지 뒤로 가기
        history.back();
    });

})