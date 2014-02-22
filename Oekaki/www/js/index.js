var xx= new Array();

function homework() {
    var cut = localStorage.length||0;
    if(cut){
        var middle = localStorage.getItem('failure');
        var result_f = eval('(' + middle + ')')||0;
        if(result_f.length == undefined){
            alert("前回は満点ですね。おめでとう!");
        }else{
            location.href ='homework.html';
        }
    }else{
        alert('難易度を選んで、１回目に挑戦しよう');
    }
}
function buttonX() {
    localStorage.removeItem("yyy");
    localStorage.removeItem("xxx");
    var options = document.getElementById('select').options;
    var value = options.item(select.selectedIndex).value;
    if(value == 1){
        xx.push(0);
        localStorage.setItem('yyy', JSON.stringify(xx));
        location.href = 'question.html';
    }else if (value == 2){
        xx.push(1);
        localStorage.setItem('yyy', JSON.stringify(xx));
        location.href = 'question.html';
    }else if(value == 3){
        xx.push(2);
        localStorage.setItem('yyy', JSON.stringify(xx));
        location.href = 'question.html';
    }else if(value == 4){
        xx.push(3);
        localStorage.setItem('yyy', JSON.stringify(xx));
        location.href = 'question.html';
    }else if(value == 5){
        xx.push(4);
        localStorage.setItem('yyy', JSON.stringify(xx));
        location.href = 'question.html';
    }else{
        alert("レベルを選択してくださいね！");
    }
}
window.addEventListener('load', function() {
    FastClick.attach(document.body);
}, false);
var nend_params = {"media":7071,"site":56169,"spot":120944,"type":1,"oriented":3};