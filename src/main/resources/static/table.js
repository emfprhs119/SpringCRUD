$(document).ready(function(){
	var actions = $("table td:last-child").html();
	// Append table with add row form on add new button click
	/*
    $(".add-new").click(function(){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
            '<td><input type="text" class="form-control" name="department" id="department"></td>' +
            '<td><input type="text" class="form-control" name="phone" id="phone"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);
		$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });
    */

    /*
    @author https://github.com/macek/jquery-serialize-object
    */
    $.fn.serializeObject = function () {
      'use strict';
      var result = {};
      var extend = function (i, element) {
        var node = result[element.name];
        if ('undefined' !== typeof node && node !== null) {
          if ($.isArray(node)) {
            node.push(element.value);
          } else {
            result[element.name] = [node, element.value];
          }
        } else {
          result[element.name] = element.value;
        }
      };

      $.each(this.serializeArray(), extend);
      return result;
    };
    $(document).on("click", "#submitButton", function(event){
        const serializedValues2 = $('#addForm').serializeObject();
        console.log(JSON.stringify(serializedValues2));
        $.ajax({
            type : 'post',
            url : '/api/v1/mock',
            contentType: 'application/json',
            data : JSON.stringify(serializedValues2),
            dataType : 'json',
            error: function(xhr, status, error){
                alert('데이터 생성 실패');
            },
            success : function(json){
                alert('데이터 생성 완료')
            },
        });
    });
	// Add row on add button click
	$(document).on("click", ".add", function(){
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');
        input.each(function(){
			if(!$(this).val()){
				$(this).addClass("error");
				empty = true;
			} else{
                $(this).removeClass("error");
            }
		});
		$(this).parents("tr").find(".error").first().focus();
		if(!empty){
			input.each(function(){
				$(this).parent("td").html($(this).val());
			});
			$(this).parents("tr").find(".add, .edit").toggle();
			$(".add-new").removeAttr("disabled");
		}
    });
	// Edit row on edit button click
	$(document).on("click", ".edit", function(){
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
		});
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
        //$(this).parents("tr").remove();
        var result = "";
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
            result = result+$(this).text()+"</br>";
        });
        console.log(result);
        $("#delData").html(result);
		$(".add-new").removeAttr("disabled");

    });
});