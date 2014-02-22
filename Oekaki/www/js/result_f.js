var middle = localStorage.getItem('failure');
var result_f = eval('(' + middle + ')');

var qtword0 = ['baker','bamboo','area','barn','cousin','culture','fence','field',
'forest','future','gift','grade','habit','health','height','history','information',
'interest','journey','kind','lake','magazine','medicine','manager','matter',
'opinion','newspaper','plant','price','prize','reason','recipe','rule','shade',
'tourist','weather','wedding','aquarium','century','clothes','college','contact',
'engine','entrance','example','grandparents','gym','director','order','parachute',
'octopus','environment'];

var qtword1 = ['absence','capacity','blood','admiration','advertisement','labor',
'production','argument','ash','atom','bill','benefit','ballet','civilization',
'clerk','community','content','crowd','degree','detail','disease','clue',
'patience','effect','pear','evidence','experience','experiment','factor',
'feature','government','conclusion','industry','issue','knowledge','literature',
'mass','material','method','object','party','principle','relation','species','subject',
'temperature','variety','president','anxiety','agriculture','authority','consumption',
'continent','contribution','curiosity','debt','employee','evolution','fate','fuel',
'impression','mammal','myth','appetite','dignity','grave','suburb','relief','ritual',
'confusion','dispute','immigration','mankind','portion','companion','grief','despair',
'junk'];

var qtword2 = ['triumph','compliment','affection','revenue','astronomy','tissue',
'comrade','emphasis','personnel','fury','heir','aisle','heritage','burden','transaction',
'cave','committee','composition','proceeds','contempt','grocery','commodity',
'aristocrat','enterprise','enthusiasm','extinction','banquet','premise','refreshment',
'infant','inhabitant','insight','instinct','insurance','load','lord','lung','objective',
'coverage','dock','perception','perspective','recession','prose','prejudice','lid',
'pursuit','reference','association','ritual','sacrifice','servant','statistics','dependent',
'telescope','tide','tragedy','trait','trail','virtue','ambulance','candidate','rally',
'dawn','sensation','hedge','mess','psychologist','skyscraper','overhead','attire','compromise',
'imitation','intersection','jar','jaw','lamb','pea','horn','plow','prescription',
'delinquency','aviation','parliament','disarmament','folks','venture','astronomer',
'Antarctica','consequence','yield','strain','aquisition','apparatus','earnings','fluid',
'odor','gulf','postage','proceeding','carrier','speculation','faculty','plunge'];

var qtword3 = ['tailor','contingency','pine','quota','Galaxy','frost',
'wrinkle','Arctic','fireplace','chimney','yacht','atlas','conscience','saw',
'brink','sanctuary','utensil','hippopotamus','livestock','barley','menace',
'Venus','hare','duel','limb','longitude','casuality','consciousness','veteran',
'brook','hedonism','pact','haze','timber','nest','astronomy','sovereign',
'validity','gult','lighthouse','moss','usher','pail','salute','walnut','crow',
'dove','chronicle','gauze','strait','dwarf','peasant','orchard','coward',
'hatred','convex','taxation','dandelion','clam','drizzle','envoy','parrot',
'cedar','shed','cuttlefish','Jupiter','bud','ingenuity','chunk','basin',
'ostrich','peril','obituary','meteorology','reminiscence','curator','constituency',
'dough','fleet','furor','pathology','eulogy'];

var qtword4 = ['partisan','plaintiff','presumption','shed','legacy','tariff',
'peacock','fusion','acme','anarchist','nausea','pneumonia','tract','diminution',
'Temperate Zone','melancholy','census','hail','philanthropist','terra','prosecution',
'defiance','fanaticism','stalk','inception','oath','deference','wrath','shred',
'Brussels sprouts','conviction','outset','optician','bowel','lace','maneuver',
'rhinoceros','bean sprouts','seclusion','glossary','rally','oblivion','mineralogy',
'scapegoat','segregation','tuberculosis','archaeology','momentum','hearth',
'swamp','ethnology','jurisdiction','affliction','setback','enmity','tidal wave',
'seismology','avarice','herring','ascendancy','credulity','subsistance','woe',
'ardor','paddy field','jellyfish','pasteurization','savant','sprain','condolence',
'archipelago','outlet','raid','runoff','forbearance','candor','levity','perch',
'zinc','hemorrhage','narcotic','impetus','novelty','fervor','gale','clemency',
'remorse','larva','sparrow','wedge','plateau','deadlock','Uranus','cactus',
'coffin','foe','ranch','limestone','aggression','rhetoric','vegetation','mirage',
'salvation','outskirts','estuary','opportunist','dune','concord','jeopardy',
'coercion','idiosyncrasy','primate','censure'];

var qtword = [qtword0,qtword1,qtword2,qtword3,qtword4];

var tango0 = {'baker':'パン職人','bamboo':'竹','area':'地域','barn':'納屋','cousin':'いとこ',
'culture':'文化','fence':'フェンス','field':'原野','forest':'森','future':'未来',
'gift':'贈り物','grade':'成績','habit':'習慣','health':'健康','height':'高さ','history':'歴史',
'information':'情報','interest':'興味','journey':'旅','kind':'種類','lake':'湖',
'magazine':'雑誌','medicine':'薬','manager':'管理人','matter':'問題','opinion':'意見',
'newspaper':'新聞','plant':'植物','price':'価格','prize':'賞','reason':'理由','recipe':'レシピ',
'rule':'規則','shade':'日陰','tourist':'旅行者','weather':'天候','wedding':'結婚式',
'aquarium':'水族館','century':'世紀','clothes':'洋服','college':'大学','contact':'接触',
'engine':'エンジン','entrance':'入り口','example':'例','grandparents':'祖父母','gym':'体育館',
'director':'監督','order':'順序','parachute':'パラシュート','octopus':'たこ','environment':'環境'};

var tango1 = {'absence':'欠席','capacity':'最大容量','blood':'血液','admiration':'賞賛',
'advertisement':'広告','labor':'労働','production':'生産','argument':'議論','ash':'灰',
'atom':'原子','bill':'請求書','benefit':'利益','ballet':'バレエ','civilization':'文明',
'clerk':'事務員','community':'共同社会','content':'内容','crowd':'群れ','degree':'程度',
'detail':'詳細','disease':'病気','clue':'手がかり','patience':'忍耐','effect':'効果','pear':'梨',
'evidence':'証拠','experience':'経験','experiment':'実験','factor':'労働','feature':'特徴',
'government':'政府','conclusion':'結論','industry':'産業','issue':'問題','knowledge':'知識',
'literature':'文学','mass':'集まり','material':'物質','method':'方法','object':'物体',
'party':'政党','principle':'原則','relation':'関係','species':'種類','subject':'主題',
'temperature':'気温','variety':'多様性','president':'大統領','anxiety':'心配','agriculture':'農業',
'authority':'権威','consumption':'消費','continent':'大陸','contribution':'寄付','curiosity':'好奇心',
'debt':'借金','employee':'従業員','evolution':'進化','fate':'運命','fuel':'燃料','impression':'印象',
'mammal':'ほ乳類','myth':'神話','appetite':'食欲','dignity':'尊厳','grave':'墓','suburb':'郊外',
'relief':'安心','confusion':'混乱','dispute':'論争','immigration':'移住','mankind':'人間',
'portion':'部分','companion':'仲間','grief':'悲しみ','despair':'絶望','junk':'がらくた'};

var tango2 = {'triumph':'勝利','compliment':'賛辞','affection':'愛情','revenue':'歳入',
'astronomy':'天文学','tissue':'組織','comrade':'仲間','emphasis':'強調','personnel':'人事部',
'fury':'激怒','heir':'相続人','aisle':'通路','heritage':'遺産','burden':'重荷','transaction':'取引',
'cave':'洞窟','committee':'委員会','composition':'作文','proceeds':'利益','contempt':'軽蔑',
'grocery':'食料雑貨類','commodity':'商品','aristocrat':'貴族','enterprise':'企業','enthusiasm':'熱意',
'extinction':'絶滅','banquet':'宴会','premise':'前提','refreshment':'軽食','infant':'乳幼児',
'inhabitant':'住人','insight':'洞察','instinct':'本能','insurance':'保証','load':'積み荷',
'lord':'支配者','lung':'肺','objective':'目標','coverage':'保証範囲','dock':'波止場','perception':'知覚',
'perspective':'見方','recession':'景気後退','prose':'散文','prejudice':'偏見','lid':'蓋',
'pursuit':'追求','reference':'参照','association':'協会','ritual':'儀式','sacrifice':'犠牲',
'servant':'使用人','statistics':'統計学','dependent':'扶養家族','telescope':'顕微鏡','tide':'潮',
'tragedy':'悲劇','trait':'特徴','trail':'痕跡','virtue':'美徳','ambulance':'救急車','candidate':'候補者',
'rally':'回復','dawn':'夜明け','sensation':'感覚','hedge':'垣根','mess':'混乱','psychologist':'哲学者',
'skyscraper':'超高層ビル','overhead':'諸経費','attire':'服装','compromise':'妥協','imitation':'ものまね',
'intersection':'交差点','jar':'瓶','jaw':'あご','lamb':'子羊','pea':'エンドウ豆','horn':'クラクション',
'plow':'すき','prescription':'処方箋','delinquency':'非行','aviation':'航空','parliament':'国会',
'disarmament':'軍備縮小','folks':'両親','venture':'事業','astronomer':'天文学者','Antarctica':'南極大陸',
'consequence':'結果','yield':'報酬','strain':'負担','aquisition':'買収','apparatus':'器具',
'earnings':'報酬','fluid':'水分','odor':'悪臭','gulf':'隔たり','postage':'郵便料金','proceeding':'議事録',
'carrier':'運送会社','speculation':'推測','faculty':'学部','plunge':'株価の急落'};

var tango3 = {'tailor':'洋服屋','contingency':'偶然性','pine':'マツ','quota':'割当','Galaxy':'天の川',
'frost':'霜','wrinkle':'しわ','Arctic':'北極','fireplace':'暖炉','chimney':'煙突','yacht':'ヨット',
'atlas':'地図帳','conscience':'良心','saw':'のこぎり','brink':'崖っぷち','sanctuary':'自然保護区',
'utensil':'家庭用品','hippopotamus':'カバ','livestock':'家畜','barley':'大麦','menace':'脅迫',
'Venus':'金星','hare':'野ウサギ','duel':'決闘','limb':'手足','longitude':'経度','casuality':'災難',
'consciousness':'良心','veteran':'ベテラン','brook':'小川','hedonism':'快楽主義','pact':'条約',
'haze':'もや','timber':'木材','nest':'巣','bracket':'階層区分','sovereign':'主権者','validity':'妥当性',
'gult':'満腹','lighthouse':'灯台','moss':'コケ','usher':'案内係','pail':'バケツ','salute':'あいさつ',
'walnut':'クルミ','crow':'カラス','dove':'ハト','chronicle':'年代記','gauze':'ガーゼ','strait':'海峡',
'dwarf':'小人','peasant':'農民','orchard':'果樹園','coward':'臆病者','hatred':'憎しみ','convex':'凸状',
'taxation':'課税','dandelion':'たんぽぽ','clam':'二枚貝','drizzle':'霧雨','envoy':'外交使節',
'parrot':'オオム','cedar':'スギ','shed':'小屋','cuttlefish':'イカ','Jupiter':'木星','bud':'つぼみ',
'ingenuity':'創意','chunk':'塊','basin':'洗面器','ostrich':'ダチョウ','peril':'危険','obituary':'死亡記事',
'meteorology':'気象学','reminiscence':'思い出','curator':'館長','constituency':'有権者',
'dough':'パン生地','fleet':'入り江','furor':'熱狂','pathology':'病理学','eulogy':'賛辞'};

var tango4 = {'partisan':'同士','plaintiff':'原告','presumption':'推測','shed':'小屋','legacy':'遺産',
'tariff':'関税','peacock':'クジャク','fusion':'融解','acme':'絶頂','anarchist':'無政府主義者',
'nausea':'吐き気','pneumonia':'肺炎','tract':'地域','diminution':'現象','Temperate Zone':'温帯',
'melancholy':'憂うつ','census':'国勢調査','hail':'ひょう','philanthropist':'博愛主義者','terra':'土地',
'prosecution':'起訴','defiance':'挑戦','fanaticism':'熱狂','stalk':'茎','inception':'開始','oath':'誓い',
'deference':'服従','wrath':'激怒','shred':'一片','Brussels sprouts':'芽キャベツ','conviction':'有罪判決',
'outset':'着手','optician':'検眼士','bowel':'はらわた','lace':'靴ひも','maneuver':'作戦行動',
'rhinoceros':'サイ','bean sprouts':'もやし','seclusion':'隔離','glossary':'用語解説','rally':'集会',
'oblivion':'人事不省','mineralogy':'鉱物学','scapegoat':'身代わり','segregation':'隔離',
'tuberculosis':'結核','archaeology':'考古学','momentum':'運動量','hearth':'炉','swamp':'沼地',
'ethnology':'民族学','jurisdiction':'管轄権','affliction':'苦痛','setback':'妨げ','enmity':'敵意',
'tidal wave':'高潮','seismology':'地震学','avarice':'どん欲','herring':'ニシン','ascendancy':'優位',
'credulity':'信じ込む性格','subsistance':'生存','woe':'悲痛','ardor':'熱意','paddy field':'水田',
'jellyfish':'くらげ','pasteurization':'低温殺菌','savant':'博学な人','sprain':'ねんざ','condolence':'追悼',
'archipelago':'群島','outlet':'出口','raid':'急襲','runoff':'雨水','forbearance':'忍耐','candor':'素直さ',
'levity':'軽率','perch':'止まり木','zinc':'亜鉛','hemorrhage':'大出血','narcotic':'麻酔薬',
'impetus':'機動力','novelty':'珍しさ','fervor':'熱情','gale':'疾風','clemency':'寛大な処置',
'remorse':'悔恨の念','larva':'幼虫','sparrow':'すずめ','wedge':'くさび','plateau':'高原',
'deadlock':'行き詰まり','Uranus':'天王星','cactus':'サボテン','coffin':'棺','foe':'敵','ranch':'牧場',
'limestone':'石灰岩','aggression':'侵害','rhetoric':'修辞学','vegetation':'植物','mirage':'蜃気楼',
'salvation':'救済','outskirts':'郊外','estuary':'河口','opportunist':'日和見主義者','dune':'砂丘',
'concord':'一致','jeopardy':'危険','coercion':'強制力','idiosyncrasy':'特異性','primate':'霊長類',
'censure':'非難'};

var tango = [tango0,tango1,tango2,tango3,tango4];
/*
var links0 = {};
for( var i = 0; i < 52; i++){
links0[qtword0[i]]='http://ejje.weblio.jp/content/'+qtword0[i];
}
var links1 = {};
for( var i = 0; i < 77; i++){
links1[qtword1[i]]='http://ejje.weblio.jp/content/'+qtword1[i];
}
var links2 = {};
for( var i = 0; i < 104; i++){
links2[qtword2[i]]='http://ejje.weblio.jp/content/'+qtword2[i];
}
var links3 = {};
for( var i = 0; i < 82; i++){
links3[qtword3[i]]='http://ejje.weblio.jp/content/'+qtword3[i];
}
var links4 = {};
for( var i = 0; i < 113; i++){
links4[qtword4[i]]='http://ejje.weblio.jp/content/'+qtword4[i];
}
var links =[links0,links1,links2,links3,links4];

var links0 = {baker:'http://ejje.weblio.jp/content/baker',
bamboo:'https://www.google.co.jp/search?q=bamboo+%E8%8B%B1%E8%AA%9E&oq=bamboo+%E8%8B%B1%E8%AA%9E&aqs=chrome..69i57.10481j0j7&sourceid=chrome&espv=210&es_sm=91&ie=UTF-8',
octopus:'http://ejje.weblio.jp/content/octopus'};
*/
var numbers0 = [];
var numbers1 = [];
var numbers2 = [];

for(var l=0; l <= 9; l++){
    numbers0[l] = new Image();
	numbers0[l].src = "./image/number/score_a/score_a"+l+".png";
}
for(var m=0; m <= 14; m++){
	numbers1[m] = new Image();
	numbers1[m].src = "./image/number/score_b/score_b"+m+".png";
}
for(var n=0; n <= 19; n++){
	numbers2[n] = new Image();
	numbers2[n].src = "./image/number/score_c/score_c"+n+".png";
}

var numbers = [numbers0,numbers1,numbers2];

var zzz = localStorage.getItem('yyy');
var level = eval('(' + zzz + ')');

function comment(){
	var advice = document.getElementById("advice");
	var seikaisu = document.getElementById("seikaisu");
	var img_com = document.createElement('img');
	var numberS = document.createElement('img');
  switch (level[0]) {
  case 0:
    count = 10;
    if(result_f.length == 1 || result_f.length == 2 ){
		img_com.src = "./image/comments/level0-2.png";	
	}else if(result_f.length < 6 && result_f.length > 2){
		img_com.src = "./image/comments/level0-1.png";	
	}else{
		img_com.src = "./image/comments/level0-0.png";	
	}
	numberS.src = numbers0[count - result_f.length].src;
    break;
  case 1:
    count = 15;
    if(result_f.length == 1 || result_f.length == 2 ){
		img_com.src = "./image/comments/level1-4.png";	
	}else if(result_f.length == 3 || result_f.length == 4){
		img_com.src = "./image/comments/level1-3.png";	
	}else if(result_f.length < 8 && result_f.length >= 5){
		img_com.src = "./image/comments/level1-2.png";	
	}else if(result_f.length < 11 && result_f.length >= 8){
		img_com.src = "./image/comments/level1-1.png";	
	}else{
		img_com.src = "./image/comments/level1-0.png";	
	}
	numberS.src = numbers1[count - result_f.length].src;
    break;
  case 2:
    count = 20;
    if(result_f.length == 1 || result_f.length == 2 ){
		img_com.src = "./image/comments/level2-6.png";	
	}else if(result_f.length == 3 || result_f.length == 4){
		img_com.src = "./image/comments/level2-5.png";	
	}else if(result_f.length == 5 || result_f.length == 6){
		img_com.src = "./image/comments/level2-4.png";	
	}else if(result_f.length == 7 || result_f.length == 8){
		img_com.src = "./image/comments/level2-3.png";	
	}else if(result_f.length < 12 && result_f.length >= 9){
		img_com.src = "./image/comments/level2-2.png";	
	}else if(result_f.length < 15 && result_f.length >= 12){
		img_com.src = "./image/comments/level2-1.png";	
	}else{
		img_com.src = "./image/comments/level2-0.png";	
	}
	numberS.src = numbers2[count - result_f.length].src;
    break;
  case 3:
    count = 15;
    if(result_f.length == 1 || result_f.length == 2 ){
		img_com.src = "./image/comments/level3-4.png";	
	}else if(result_f.length == 3 || result_f.length == 4){
		img_com.src = "./image/comments/level3-3.png";	
	}else if(result_f.length < 8 && result_f.length >= 5){
		img_com.src = "./image/comments/level3-2.png";	
	}else if(result_f.length < 11 && result_f.length >= 8){
		img_com.src = "./image/comments/level3-1.png";	
	}else{
		img_com.src = "./image/comments/level3-0.png";	
	}
	numberS.src = numbers1[count - result_f.length].src;
    break;
　case 4:
    count = 20;
    if(result_f.length == 1 || result_f.length == 2 ){
		img_com.src = "./image/comments/level4-5.png";	
	}else if(result_f.length == 3 || result_f.length == 4){
		img_com.src = "./image/comments/level4-4.png";	
	}else if(result_f.length == 5 || result_f.length == 6){
		img_com.src = "./image/comments/level4-3.png";	
	}else if(result_f.length == 7 || result_f.length == 8){
		img_com.src = "./image/comments/level4-2.png";	
	}else if(result_f.length == 10 || result_f.length == 9){
		img_com.src = "./image/comments/level4-1.png";	
	}else{
		img_com.src = "./image/comments/level4-0.png";	
	}
	numberS.src = numbers2[count - result_f.length].src;
    break;
  default:
    alert("エラーや！");
    break;
  }
	advice.appendChild(img_com);
	seikaisu.appendChild(numberS);
}
function judge(){
	for(var i=0; i < result_f.length ;i++){
		for(var k=0; k < qtword[level[0]].length ;k++){
			if(result_f[i]== qtword[level[0]][k]){
				var tb = document.getElementById("tb");
				var tr = document.createElement('tr');
				var td1 = document.createElement('td');
				var td2 = document.createElement('td');
                /*
				var td3 = document.createElement('td');
				var internet = document.createElement('img');
				internet.src = "./image/internet.png";
				internet.className = "internet";
                function ref(){
                var ref = window.open(links[level[0]][qtword[level[0]][k]], '_blank', 'location=yes'); 
                }
                internet.addEventListener('click',ref,false);
                */
				td1.appendChild(document.createTextNode(qtword[level[0]][k]));
				td2.appendChild(document.createTextNode(tango[level[0]][qtword[level[0]][k]]));
                /*
				td3.appendChild(internet);
                */
				tb.appendChild(tr);
				tr.appendChild(td1);
				tr.appendChild(td2);
				//tr.appendChild(td3);
			}
		}
	}
}
function ind() {
    location.href = 'index.html';
}
function again() {
    location.href = 'question.html';
}
window.onload = function(){
	judge();
	comment();
}
window.addEventListener('load', function() {
    FastClick.attach(document.body);
}, false);
monaca.viewport({width: "640"});