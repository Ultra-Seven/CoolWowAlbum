/*javascript for message page
 * created by XuHailong
 */


var num ;
var label ;

$(document).ready(function()
{
	$('#nav').spasticNav();
	
	function alert_modal(inf)
	{
		$("#modal-text").html(inf);
		$('#infModal').modal('show');
	}
	
	label = $("#label").html();
	num = $("#currentpage" + label).html() - 0 + 1;
	
	id = $('#loggeduserid').html();
	name = $('#loggeduser').html();

	
	//active the label of current page
	$("#pagination1 li:nth-child(2)").addClass("active");
	$("#pagination2 li:nth-child(2)").addClass("active");
	$("#pagination" + label + " li:nth-child(" + num + ")").addClass("active");
	if(num != 2)
		$("#pagination" + label + " li:nth-child(2)").removeClass("active");
	
	//active current label
	//label = label - 1;
	$("#tabs li:nth-child(" + label + ") a").tab('show'); 

	//buttons
	$(".replybtn").click
	(function()
			{
				$("#input1").attr("value",$(this).parent().parent().parent().children("th").text().trim() );
				$("#tab3 a").tab("show");
				return false;
			}
			
	
	);
	
	//validate message
	$("#submit1").click
	(function() 
	{	
		if($("#input1").val() == "")
		{
			alert_modal("请输入收信人!");  
			return false; 
		}
		if($("#input2").val() == "" )
		{
			alert_modal("请输入内容!");  
			return false; 
		}
		return true;  
	});
	
	//success modal
	if($("#inf").html() == "suc")
		{
			$("#modal-text").html("操作成功！");
			$('#infModal').modal('show');
		}
	
	if($("#inf").html() == "wrg")
	{
		$("#modal-text").html("操作失败！");
		$('#infModal').modal('show');
	}
	
	if($("#inf").html() == "inv")
	{
		$("#modal-text").html("该用户不存在！");
		$('#infModal').modal('show');
	}
});
