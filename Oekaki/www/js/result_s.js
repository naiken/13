var img00 = new Image();
img00.src = "./image/nigaoe/saru/saru9.png";
var img01 = new Image();
img01.src = "./image/nigaoe/fish/fish9.png";
var img10 = new Image();
img10.src = "./image/nigaoe/otama/otama14.png";
var img11 = new Image();
img11.src = "./image/nigaoe/usagi/usagi14.png";
var img12 = new Image();
img12.src = "./image/nigaoe/raion/raion14.png";
var img13 = new Image();
img13.src = "./image/nigaoe/boy/boy14.png";
var img20 = new Image();
img20.src = "./image/nigaoe/pic/pic19.png";
var img21 = new Image();
img21.src = "./image/nigaoe/inu/inu19.png";
var img22 = new Image();
img22.src = "./image/nigaoe/panda/panda19.png";
var img23 = new Image();
img23.src = "./image/nigaoe/neko/neko19.png";

var img0 = [img00,img01];
var img1 = [img10,img11,img12,img13];
var img2 = [img20,img21,img22,img23];
var img_last = [img0,img1,img2];

var com0 = new Image();
com0.src = "./image/comments/level0-3.png";
var com1 = new Image();
com1.src = "./image/comments/level1-5.png";
var com2 = new Image();
com2.src = "./image/comments/level2-7.png";
var com3 = new Image();
com3.src = "./image/comments/level3-5.png";
var com4 = new Image();
com4.src = "./image/comments/level4-6.png";

var com = [com0,com1,com2,com3,com4];

var zzz = localStorage.getItem('yyy');
var level = eval('(' + zzz + ')');

function last_pic(){
    var pic = document.getElementById("pic");
	var irasuto = document.createElement("img");
	if(level[0] == 0){
	irasuto.src = img_last[0][localStorage.getItem('xxx')].src;
	}else if(level[0] ==1 || level[0]==3 ){
	irasuto.src = img_last[1][localStorage.getItem('xxx')].src;
	}else{
	irasuto.src = img_last[2][localStorage.getItem('xxx')].src;
	}
	irasuto.id = "irasuto";
	pic.appendChild(irasuto);
}
function comment(){
	var comments = document.getElementById("comment2");
	var coms = document.createElement("img");
	coms.src = com[level[0]].src;
	coms.id = "coms";
	pic.appendChild(coms);
}
function ind() {
    location.href = 'index.html';
}
function again() {
    location.href = 'question.html';
}
window.onload = function(){
	comment();
	last_pic();
}
monaca.viewport({width: "640"});