// This is a JavaScript file

enchant();

enchant.Sound.enabledInMobileSafari = true;

//進化情報を保持する変数
var evo = 0;
var pon = 0;
var evo_root = 0;
var evo_count = 0;

var leaf_countR = 0;
var leaf_countB = 0;

var sheight = screen.height;
var swidth = screen.width;


// This is a JavaScript file

//ローカルストレージに葉っぱの保存
function save_leaf(red_leaf,blue_leaf){
    var leaf = {
        //赤い葉っぱの保存
        RLkeep : red_leaf,
        //青い葉っぱの保存
        BLkeep : blue_leaf
    };
    localStorage.setItem('leaves',JSON.stringify(leaf));
};


//葉っぱ赤青どっちもの取り出し
function get_leaf(){
    var leaf_get = JSON.parse(localStorage.getItem('leaves'));
    return leaf_get;
};


//青い葉っぱの取り出し
function get_blue_leaf() {
    var leaf_get = JSON.parse(localStorage.getItem('leaves'));
    return leaf_get['BLkeep'];
};

//赤い葉っぱの取り出し
function get_red_leaf() {
    var leaf_get = JSON.parse(localStorage.getItem('leaves'));
    return leaf_get['RLkeep'];
};

//ローカルストレージに進化状況を保存
function save_evolution(evo_save){
    var evo_tanuki = {
        //たぬきの進化状況の保存
        evoSave : evo_save
    };
    localStorage.setItem('evolutions',JSON.stringify(evo_tanuki));
}

//雄雌どっちもたぬきの取り出し
function get_tanuki() {
    var evo_tanu_get = JSON.parse(localStorage.getItem('evolutions'));
    return evo_tanu_get;
}

//腕男たぬきの取り出し
function get_evo_tanuki() {
    var evo_tanu_get = JSON.parse(localStorage.getItem('evolutions'));
    return evo_tanu_get['evoSave'];
};

//図鑑の画像表示フラグ変数,メモリ確保
function save_detail(){
    var detail_library = {
        detail0 : 1,
        detail1 : 0,
        detail2 : 0,
        detail3 : 0,
        detail4 : 0,
        detail5 : 0,
        detail6 : 0,
        detail7 : 0,
        detail8 : 0,
        detail9 : 0,
        detail10 : 0,
        detail11 : 0,
        detail12 : 0,
        detail13 : 0,
        detail14 : 0,
        detail15 : 0,
        detail16 : 0,
        detail17 : 0,
        detail18 : 0,
        detail19 : 0,
        detail20 : 0,
        detail21 : 0,
        detail22 : 0,
        detail23 : 0,
        detail24 : 0,
        detail25 : 0,
        detail26 : 0,
        detail27 : 0,
        detail28 : 0,
        detail29 : 0,
        detail30 : 0,
    };

    localStorage.setItem('Detail', JSON.stringify(detail_library));
}

//図鑑のフラグ変数全部取り出し
function get_detail(){
    var get_detail_all = JSON.parse(localStorage.getItem('Detail'));
    return get_detail_all;
}

//図鑑のフラグ変数一部取り出し
function get_detail_number(dt){
    var get_detail = JSON.parse(localStorage.getItem('Detail'));
    var number = 'detail' + dt;
    return get_detail[number];
}

//図鑑のフラグ変数の更新
function update_detail(dt){
    var detail_library = JSON.parse(localStorage.getItem('Detail'));
    var number_dt = String(dt);
    var number = 'detail' + number_dt;
    detail_library[number] = 1;
    localStorage.setItem('Detail', JSON.stringify(detail_library));
}

//ローカルストレージのメモリ確保
save_detail();
save_evolution(evo_count);
save_leaf(leaf_countR,leaf_countB);


window.onload = function(){


    var game = new Game(640,960);

    game.fps = 24;

    game.preload('./img/leaf_blue.png', './img/leaf_red.png', './img/leaf_yellow.png'
                    , './img/parameter.png','./img/change.png','./img/start_title.png','./img/splash.png'
                    ,'./img/start_background.png','./img/book02.png'/*,'./BGM/BGM1.mp3'*/
                    ,'./img/book04.png','./img/splash.png','./img/library/0.png','./img/library/1.png'
                    ,'./img/modoru.png','./img/tsugi.png'
                    ,'./img/clear01.png','./img/clear02.png','./img/clear03.png'
                    ,'./img/library/2.png','./img/library/3.png','./img/library/4.png'
                    ,'./img/library/5.png','./img/library/6.png','./img/library/7.png'
                    ,'./img/library/8.png','./img/library/9.png','./img/library/10.png'
                    ,'./img/library/11.png','./img/library/12.png','./img/library/13.png'
                    ,'./img/library/14.png','./img/library/15.png','./img/library/16.png'
                    ,'./img/library/17.png','./img/library/18.png','./img/library/19.png'
                    ,'./img/library/20.png','./img/library/21.png','./img/library/22.png'
                    ,'./img/library/23.png','./img/library/24.png','./img/library/25.png'
                    ,'./img/library/26.png','./img/library/27.png','./img/library/28.png'
                    ,'./img/library/29.png','./img/library/30.png','./img/library/31.png'
                    ,'./img/tanuki.png'
                    ,'./img/1ude.png'
                    ,'./img/1ude_1asi.png'
                    ,'./img/1ude_1asi_1body.png'
                    ,'./img/1ude_1asi_1body_1face.png'
                    ,'./img/1ude_1asi_1body_2face.png'
                    ,'./img/1ude_1asi_2body.png'
                    ,'./img/1ude_1asi_2body_1face.png'
                    ,'./img/1ude_1asi_2body_2face.png'
                    ,'./img/1ude_2asi.png'
                    ,'./img/1ude_2asi_1body.png'
                    ,'./img/1ude_2asi_1body_1face.png'
                    ,'./img/1ude_2asi_1body_2face.png'
                    ,'./img/1ude_2asi_2body.png'
                    ,'./img/1ude_2asi_2body_1face.png'
                    ,'./img/1ude_2asi_2body_2face.png'
                    ,'./img/2ude.png'
                    ,'./img/2ude_1asi.png'
                    ,'./img/2ude_1asi_1body.png'
                    ,'./img/2ude_1asi_1body_1face.png'
                    ,'./img/2ude_1asi_1body_2face.png'
                    ,'./img/2ude_1asi_2body.png'
                    ,'./img/2ude_1asi_2body_1face.png'
                    ,'./img/2ude_1asi_2body_2face.png'
                    ,'./img/2ude_2asi.png'
                    ,'./img/2ude_2asi_1body.png'
                    ,'./img/2ude_2asi_1body_1face.png'
                    ,'./img/2ude_2asi_1body_2face.png'
                    ,'./img/2ude_2asi_2body.png'
                    ,'./img/2ude_2asi_2body_1face.png'
                    ,'./img/2ude_2asi_2body_2face.png');




    game.onload = function(){



        //葉っぱの速度、初期化変数
        var vy = 80;
        var vx = 60;
        var sl = 2/3;

        //時間の初期化
        var time_l = 0;
        var time_t = 0;

        //葉っぱの配列数
        var k = 0;
        var j = 0;

//        var bx = new Array(5);
//        var by = new Array(5);
//        var rx = new Array(5);
//        var ry = new Array(5);

        //葉っぱの取得枚数
        var leaf_count = 0;


        //葉っぱのローカルストレージからの取得
        var countR =  get_blue_leaf();
        var countB =  get_red_leaf();
        var EvoCount = get_evo_tanuki();

        //変数の更新
        leaf_countR = countR;
        leaf_countB = countB;
        evo_count = EvoCount;

        //葉っぱのスプライト保存
        var leaf_groupB = new Sprite();
        var leaf_groupR = new Sprite();
        var evoTouch = new Sprite();

        //葉っぱの色フラグ
        var cFlag = 0;

        //BGMファイルの格納
        //var sound = game.assets['./BGM/BGM1.mp3'];

        //図鑑遷移ボタン選択
        var openClose = 1;


    //たぬき図鑑画像のスプライト初期設定
    var tanukiPic = enchant.Class.create(enchant.Sprite,{
        initialize: function(xi,xj,yk,gameScene,TanukiSP) {
            enchant.Sprite.call(this,140,150);

            //フラグ変数の取得
            var detail = get_detail();
            var num = 'detail' + (xi-1);

            if(detail[num] == 0){
                this.image = game.assets['./img/library/0.png'];
                this.x = xj;
                this.y = yk;
                gameScene.addChild(this);

            } else if(detail[num] == 1){
                this.image = game.assets['./img/library/' + xi + '.png'];
                this.x = xj;
                this.y = yk;
                this.state = 0;

                //画面中央拡大アニメーション
                this.on('touchstart',function(e){
                    if(this.state == 0){
                        TanukiSP = this;
                        this.parentNode.removeChild(this);
                        gameScene.addChild(TanukiSP);
                        TanukiSP.tl.moveTo(320-70,480-75,24)
                            .and().scaleTo(3,3,24);
                        this.state = 1;

                        }else {
                        this.tl.moveTo(xj,yk,24)
                            .and().scaleTo(1,1,24);
                        this.state = 0;
                        }
                    });
                    gameScene.addChild(this);
                }

            }
        });

    //たぬきの図鑑画像を配列に格納
    var TanukiPic = new Array(31);

        //タイトルシーン
        var createTitleScene = function(){

            openClose = 1;
            var scene = new Scene();

            scene.backgroundColor = "rgba(0,0,0,0.5)";

//            //少し暗い背景の挿入
//            var background = new Sprite(640,960);
//            background.image = game.assets['./img/start_background.png'];
//            background.x = 640/2-(640/2);
//            background.y = 960/2-(960/2);
//            scene.addChild(background);

            //タイトルイメージの挿入
            var titleImg = new Sprite(480,500);
            titleImg.image = game.assets['./img/start_title.png'];
            titleImg.x = 640/2-(480/2);
            titleImg.y = 960/2-(219-2);
            scene.addChild(titleImg);

            var libraryShift = new Sprite(127,112);
            libraryShift.image = game.assets['./img/book04.png'];
            libraryShift.x = 500;
            libraryShift.y = 120;
            scene.addChild(libraryShift);

            //タッチ操作でメインゲーム画面へ遷移
//           scene.on('touchstart', function(e){
//               game.replaceScene(createMainScene());
//                   game.load('./BGM/BGM1.mp3',function(){
//                       game.assets['./BGM/BGM1.mp3'].clone().play();
//                       game.assets['./BGM/BGM1.mp3'].src.loop = true;
//                   });
//               game.replaceScene(createMainScene());
//
//            });

            titleImg.on('touchstart',function(e){

                game.replaceScene(createMainScene());
//                game.load('./BGM/BGM1.mp3',function(){
//                       game.assets['./BGM/BGM1.mp3'].clone().play();
//                       game.assets['./BGM/BGM1.mp3'].src.loop = true;
//                   });
            });

            libraryShift.on('touchstart',function(e){
                game.pushScene(libraryScene_1());
            });

            return scene;
        };

          //図鑑１ページ
        var libraryScene_1 = function(){
            var openClose = 0;

            //var scene = new ScrollableScene();
            var scene = new Scene();

            scene.backgroundColor = "rgba(0,0,0,0.5)";
//            var background = new Sprite(640,960);
//            background.image = game.assets['./img/start_background.png'];
//            background.x = 0;
//            background.y = 0;
//            scene.addChild(background);

            //メイン画面に遷移
            var libraryShift = new Sprite(127,98);
            libraryShift.image = game.assets['./img/book02.png'];
            libraryShift.x = 500;
            libraryShift.y = 60;
            scene.addChild(libraryShift);
            var Tpic = new Sprite();

            //２ページ目に遷移
            var pageShift_2 = new Sprite(140,50);
            pageShift_2.image = game.assets['./img/tsugi.png'];
            pageShift_2.x = 350;
            pageShift_2.y = 100;

            //２ページ目に遷移するイベントの追加
            pageShift_2.ontouchstart = function(){
                game.replaceScene(libraryScene_2());
            };
            scene.addChild(pageShift_2);

            libraryShift.on('touchstart', function(){
                game.popScene();
            });


            var x = 640/4-65;
            var y = 170;


            for(var i=0;i<12;i++) {
                var picture = new tanukiPic(i+1,x,y,scene,Tpic);
                x += 640/4;
                if(((i+1) % 3) == 0) {
                    x = 640/4-65;
                    y += 170;
                }
                TanukiPic[i] = picture;
            }


            return scene;
        };

        //図鑑２ページ
        var libraryScene_2 = function(){
            openClose = 0;

            var scene = new Scene();
            scene.backgroundColor = "rgba(0,0,0,0.5)";
//             var background = new Sprite(640,960);
//            background.image = game.assets['./img/start_background.png'];
//            background.x = 0;
//            background.y = 0;
//            scene.addChild(background);

            //メイン画面に遷移
            var libraryShift = new Sprite(127,98);
            libraryShift.image = game.assets['./img/book02.png'];
            libraryShift.x = 500;
            libraryShift.y = 60;
            scene.addChild(libraryShift);

            //３ページ目に遷移するスプライト作成
            var pageShift_3 = new Sprite(140,50);
            pageShift_3.image = game.assets['./img/tsugi.png'];
            pageShift_3.x = 350;
            pageShift_3.y = 100;
            scene.addChild(pageShift_3);

            //１ページ目に遷移するスプライト作成
            var pageShift_1 = new Sprite(140,50);
            pageShift_1.image = game.assets['./img/modoru.png'];
            pageShift_1.x = 200;
            pageShift_1.y = 105;
            scene.addChild(pageShift_1);

            //たぬきのアニメーションスプライトのメモリ確保
            var Tpic = new Sprite();

            var x = 640/4-65;
            var y = 170;


            for(var i=0;i<12;i++) {
                var picture = new tanukiPic(i+13,x,y,scene,Tpic);
                x += 640/4;
                if(((i+1) % 3) == 0) {
                    x = 640/4-65;
                    y += 170;
                }
                TanukiPic[i] = picture;
            }

            pageShift_1.on('touchstart',function(){
               game.replaceScene(libraryScene_1());
            });

            pageShift_3.on('touchstart',function(){
               game.replaceScene(libraryScene_3());
            });

            libraryShift.on('touchstart', function(){
                game.popScene();
            });
            return scene;
        };

        //３ページ目の生成
        var libraryScene_3 = function(){
            openClose = 0;

            var scene = new Scene();

            scene.backgroundColor = "rgba(0,0,0,0.5)";
//             var background = new Sprite(640,960);
//            background.image = game.assets['./img/start_background.png'];
//            background.x = 0;
//            background.y = 0;
//            scene.addChild(background);

            //メイン画面に遷移
            var libraryShift = new Sprite(127,98);
            libraryShift.image = game.assets['./img/book02.png'];
            libraryShift.x = 500;
            libraryShift.y = 60;
            scene.addChild(libraryShift);

            //2ページ目に遷移するスプライト作成
            var pageShift_2 = new Sprite(140,50);
            pageShift_2.image = game.assets['./img/modoru.png'];
            pageShift_2.x = 200;
            pageShift_2.y = 105;
            scene.addChild(pageShift_2);

            //たぬきのアニメーションスプライトのメモリ確保
            var Tpic = new Sprite();

            var x = 640/4-65;
            var y = 170;


            for(var i=0;i<7;i++) {
                var picture = new tanukiPic(i+25,x,y,scene,Tpic);
                x += 640/4;
                if(((i+1) % 3) == 0) {
                    x = 640/4-65;
                    y += 170;
                }
                TanukiPic[i] = picture;
            }

            //２ページ目に遷移するイベントの追加
            pageShift_2.on('touchstart',function(){
               game.replaceScene(libraryScene_2());
            });

            libraryShift.on('touchstart', function(){
                game.popScene();
            });

            return scene;

        }



        //メインシーンの作成
        var createMainScene = function() {

            openClose = 1;
            var scene = new Scene();
            //scene.backgroundColor = "rgba(0,0,0,0.5)";
            var positionT = 0;

            //パラメータのスプライト初期設定
            var Parameter = enchant.Class.create(enchant.Sprite,{
                initialize: function(){
                    enchant.Sprite.call(this,656,876/11);
                    this.image = game.assets['./img/parameter.png'];
                    this.frame = 5;
                    this.scaleY = 2/3;
                    this.x = 0;
                    this.y = 16;
                    scene.addChild(this);
                }
            });

            //葉っぱのスプライト初期設定
            var Leaf = enchant.Class.create(enchant.Sprite, {
                initialize: function(){
                    enchant.Sprite.call(this, 78,79);
                    this.y = 500*Math.random();
                    this.scaleX = sl;
                    this.scaleY = sl;
                    this.originX = 78/2;
                    this.originY = 79/2;
                }
            });

            //たぬきのメインシーンでのスプライト初期設定
            var TanukiMain = enchant.Class.create(enchant.Sprite, {
                initialize: function(dx,positionT){
                    if(dx == 0){
                        enchant.Sprite.call(this, 266, 219);
                        this.image = game.assets['./img/tanuki.png'];
                    } else if(dx == 1) {
                        enchant.Sprite.call(this, 197, 237);
                        this.image = game.assets['./img/1ude.png'];
                    } else if(dx == 2) {
                        enchant.Sprite.call(this, 223, 213);
                        this.image = game.assets['./img/2ude.png'];
                    } else if(dx == 3) {
                        enchant.Sprite.call(this, 204, 236);
                        this.image = game.assets['./img/1ude_1asi.png'];
                    } else if(dx == 4) {
                        enchant.Sprite.call(this, 572/3, 252);
                        this.image = game.assets['./img/1ude_2asi.png'];
                    } else if(dx == 5) {
                        enchant.Sprite.call(this, 617/3, 212);
                        this.image = game.assets['./img/2ude_1asi.png'];
                    } else if(dx == 6) {
                        enchant.Sprite.call(this, 480/3, 225);
                        this.image = game.assets['./img/2ude_2asi.png'];
                    } else if(dx == 7) {
                        enchant.Sprite.call(this, 467/3, 299);
                        this.image = game.assets['./img/1ude_1asi_1body.png'];
                    } else if(dx == 8) {
                        enchant.Sprite.call(this, 389/3, 231);
                        this.image = game.assets['./img/1ude_1asi_2body.png'];
                    } else if(dx == 9) {
                        enchant.Sprite.call(this, 442/3, 297);
                        this.image = game.assets['./img/1ude_2asi_1body.png'];
                    } else if(dx == 10) {
                        enchant.Sprite.call(this, 394/3, 254);
                        this.image = game.assets['./img/1ude_2asi_2body.png'];
                    } else if(dx == 11) {
                        enchant.Sprite.call(this, 450/3, 305);
                        this.image = game.assets['./img/2ude_1asi_1body.png'];
                    } else if(dx == 12) {
                        enchant.Sprite.call(this, 358/3, 231);
                        this.image = game.assets['./img/2ude_1asi_2body.png'];
                    } else if(dx == 13) {
                        enchant.Sprite.call(this, 453/3, 295);
                        this.image = game.assets['./img/2ude_2asi_1body.png'];
                    } else if(dx == 14) {
                        enchant.Sprite.call(this, 360/3, 242);
                        this.image = game.assets['./img/2ude_2asi_2body.png'];
                    }
                    this.frame = 0;
                    this.scaleX = 2/3;
                    this.scaleY = 1/2;
                    if( dx == 0){
//                        positionT.x = 0;
//                        positionT.y = 0;
                        this.x = 400 * Math.random();
                        this.y = 600 + (100 * Math.random());
                    } else {
                        this.x = positionT.x - this.width/2;
                        this.y = positionT.y - this.height/2;
                    }
                    scene.addChild(this);
                }
            });

            //たぬきの動きを表現するメソッド
            var TanukiMove = function(tanukiPos,ePos) {
                var tanukiX = tanukiPos.width;
                var tanukiY = tanukiPos.height;
                tanukiPos.frame = [0,0,1,1,2,2];
                if(ePos.x-tanukiX/2 <= tanukiPos.x-tanukiX/4){
                    tanukiPos.tl.scaleTo(-1/2,1/2,12)
                        .moveTo(ePos.x-tanukiX/2,ePos.y-tanukiY/2,24);
                } else if(ePos.x-tanukiX/2 >= tanuki.x-tanukiX/4){
                    tanukiPos.tl.scaleTo(1/2,1/2,12)
                        .moveTo(ePos.x-tanukiX/2,ePos.y-tanukiY/2,24);
                } else if(e.y >= 450) {
                    tanukiPos.frame = 0;
                }
                tanukiPos.tl.moveTo(ePos.x-tanukiX/2,ePos.y-tanukiY/2,24);
            };

            //葉っぱの取得アニメーション
            var PickUpLeaf = enchant.Class.create(enchant.Sprite,{
                initialize: function(ePos) {

                    //進化アニメーションの設定
                    enchant.Sprite.call(this,612/3,205);
                    this.image = game.assets['./img/change.png'];
                    this.x = ePos.x-266/2;
                    this.y = ePos.y-219/2;
                    this.frame = [0,0,0,1,1,1,2,2,2];
                    this.opacity = 0;

                    //アニメーション
                    this.tl.delay(24)
                        .fadeIn(36)
                        .rotateBy(180,36)
                        .fadeOut(12)
                        .then(function(){
                           this.parentNode.removeChild(this);
                        });

                    scene.addChild(this);
                }
            });

            //進化葉っぱのスプライト設定
            var evolutionLeaf = function(leaf){

                leaf.x = 320;
                leaf.image = game.assets['./img/leaf_yellow.png'];

                //アニメーションの追加
                leaf.tl.moveBy(vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                    .scaleTo(-sl,sl,6)
                    .moveBy(-vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                    .scaleTo(sl,sl,6)
                        .loop();
            };

            //進化時の付随（煙）アニメーション
            var EvolutionSmoke = enchant.Class.create(enchant.Sprite,{
                initialize: function(ePos){
                enchant.Sprite.call(this,204,205);
                this.image = game.assets['./img/change.png'];
                this.x = ePos.x-266/2;
                this.y = ePos.y-219/2;
                this.frame = [0,0,1,1,2,2];
                this.tl.scaleTo(0.2,12)
                    .scaleTo(1,12)
                    .scaleTo(0.2,12)
                    .scaleTo(1,12)
                    .scaleTo(0.2,12)
                    .scaleTo(1,12)
                    .fadeOut(48)
                    .and().rotateBy(360,12)
                    .then(function(){
                        this.parentNode.removeChild(this);
                    });
                scene.addChild(this);
                }
            });

            //たぬきの進化変数調整
            var evolutionCount = function(evoC,e){
                if(evoC == 3 || evoC == 4){
                    e = e + 1;
                } else if(evoC == 5 || evoC == 6){
                    e = e + 2;
                } else if(evoC == 7 || evoC == 8){
                    e = e + 4;
                } else if(evoC == 9 || evoC == 10){
                    e = e + 5;
                } else if(evoC == 11 || evoC == 12){
                    e = e + 7;
                } else if(evoC == 13 || evoC == 14){
                    e = e + 8;
                } else{
                    e = e;
                }

                return e;
            };

            //たぬき進化のアニメーション
            var EvolutionTanuki = function(SPtanuki,ePos) {
                SPtanuki.tl.delay(36)
                    .rotateBy(360,48)
                    .and().fadeOut(48)
                    .and().scaleTo(0.2, 48, enchant.Easing.BOUNCE_EACEOUT)
                    .cue({24: function(){

                        //進化アニメーションの煙を追加
                        var evoAnime = new EvolutionSmoke(ePos);

                        }, 144: function() {

                            //進化情報保持している変数の更新
                            evo = evo_count;
                            evo_count = evolutionCount(evo,evo_count);
                            evo_count = evo_count + evo_root;

                            //図鑑の画像表示フラグ変数の更新
                            update_detail(evo_count);

                            //ローカルストレージに進化変数の登録
                            save_evolution(evo_count);

                             if(pon == 4){
                                     var eCount = evo_count - 15;
                                    pon = 0;
                                    sceneShift(eCount);
                                    return;

                                }

                            //たぬきの画像入れ替え
                            tanuki = new TanukiMain(evo_count,ePos);
                        }
                    });
            };

            //たぬきの進化変数変動
            var Evolution = function(root,leafR,leafB){
                if(leafR-leafB < 0) {
                    root += 1;
                } else if(leafR-leafB > 0){
                    root += 2;
                } else{
                    root = 0;
                    sceneShift(16);
                    return;
                }
                return root;
            };

            //画面遷移をするメソッド
            var sceneShift = function(count){
                game.replaceScene(createEnddingScene(count));
            };

            //パラメーターのスプライト挿入
            var parameter = new Parameter();

            //図鑑遷移ボタンの作成
            var libraryShift = new Sprite(127,112);
            libraryShift.image = game.assets['./img/book04.png'];
            libraryShift.x = 500;
            libraryShift.y = 120;
            scene.addChild(libraryShift);

            libraryShift.on('touchstart',function(e){
                game.pushScene(libraryScene_1());
            });

            var posT = new Sprite();
            //たぬきのスプライト挿入
            var tanuki = new TanukiMain(evo_count,posT);

            //葉っぱを格納しとく配列宣言
            var leaf_red = new Array(999);
            for (var i = 0; i<leaf_red.length; i++) {
                var leaf = new Leaf();
                leaf_red[i] = leaf;
            }

            var leaf_blue = new Array(999);
            for (var i = 0; i<leaf_blue.length; i++) {
                    var leaf = new Leaf();
                leaf_blue[i] = leaf;
            }

            scene.on('enterframe', function(){
            time_l += 1/game.fps;
            time_t += 1/game.fps;

                //time_lの経過した時間とランダムの(0or1)変数により葉っぱの色が変わる(要検討)
                if( time_l >= 15) {
                    time_l = 0;

                    leaf_blue[k].image = game.assets['./img/leaf_blue.png'];
                    leaf_blue[k].x = 60 + 160*Math.random();
                    scene.addChild(leaf_blue[k]);

                    leaf_red[j].image = game.assets['./img/leaf_red.png'];
                    leaf_red[j].x = 320 + 160*Math.random();
                    scene.addChild(leaf_red[k]);

                    //葉っぱのアニメーションの追加
                    leaf_blue[k].tl.moveBy(vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                        .scaleTo(-sl,sl,6)
                        .moveBy(-vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                        .scaleTo(sl,sl,6)
                            .loop();

                    leaf_red[j].tl.moveBy(vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                        .scaleTo(-sl,sl,6)
                        .moveBy(-vx,vy,24,enchant.Easing.CUBIC_EASEOUT)
                        .scaleTo(sl,sl,6)
                        .loop();
                }

                //葉っぱのアニメーションループ解除(青)
                if(leaf_blue[k].y >= 610 + (100 * Math.random())){
                    leaf_blue[k].tl.unloop();
                    leaf_groupB = leaf_blue[k];

                    //青い葉っぱ取得時のイベント
                    leaf_groupB.ontouchstart = function(e) {

                        //葉っぱ取得枚数の保持
                        leaf_count += 1;
                        leaf_countB += 1;
                        if(leaf_groupR.parentNode != null){
                            //葉っぱの取得枚数をローカルストレージに保存
                            save_leaf(leaf_countR,leaf_countB);

                            leaf_groupR.parentNode.removeChild(leaf_groupR);
                            parameter.frame += 1;
                            if( parameter.frame > 10){
                                parameter.frame = 10;
                            }
                            TanukiMove(tanuki,e);
                            var leafPickUp = new PickUpLeaf(e);
                            this.parentNode.removeChild(this);
                        } else {
                            leaf_count -= 1;
                            leaf_countB -= 1;
                            save_leaf(leaf_countR,leaf_countB);
                        }

                        if(leaf_count == 2){
                            var evoLeaf = new Leaf();
                            evolutionLeaf(evoLeaf);

                            evoLeaf.on('enterframe', function(){
                                //進化葉っぱのアニメーション停止および進化イベント
                                if(evoLeaf.y >= 610 + (100 * Math.random())){
                                    evoLeaf.tl.unloop();
                                }
                            });
                            evoLeaf.ontouchstart = function(evoE){

                                pon += 1;
                                var leafPickUp = new PickUpLeaf(evoE);
                                TanukiMove(tanuki,evoE);
                                evo_root = Evolution(evo_root,leaf_countR,leaf_countB);
                                parameter.frame = 5;
                                leaf_count = 0;
                                leaf_countR = 0;
                                leaf_countB = 0;
                                this.parentNode.removeChild(this);
                                EvolutionTanuki(tanuki,evoE);

                            }


                            scene.addChild(evoTouch);
                            scene.addChild(evoLeaf);
                        }
                    };
                    scene.addChild(leaf_groupB);
                    leaf_blue[k] = null;
                    k++;


                }

                //葉っぱのアニメーションループ解除(赤)
                if(leaf_red[j].y >= 610 + (100 * Math.random())){
                    leaf_red[j].tl.unloop();
                    leaf_groupR = leaf_red[j];
                    leaf_groupR.ontouchstart = function(e) {
                        leaf_count += 1;
                        leaf_countR += 1;

                        if(leaf_groupB.parentNode != null){
                        //葉っぱの取得枚数をローカルストレージに保存
                        save_leaf(leaf_countR,leaf_countB);

                        leaf_groupB.parentNode.removeChild(leaf_groupB);
                        parameter.frame -= 1;
                        if( parameter.frame < 0){
                            parameter.frame = 0;
                        }
                        TanukiMove(tanuki,e);
                        var leafPickUp = new PickUpLeaf(e);
                        this.parentNode.removeChild(this);
                        } else{
                            leaf_count -= 1;
                            leaf_countR -= 1;
                            save_leaf(leaf_countR,leaf_countB);
                        }
                        if(leaf_count == 2){
                            var evoLeaf = new Leaf();
                            evolutionLeaf(evoLeaf);

                            evoLeaf.on('enterframe', function(){

                                //進化葉っぱのアニメーション停止および進化イベント
                                if(evoLeaf.y >= 660 + (150 * Math.random())){
                                    evoLeaf.tl.unloop();
                                }
                            });
                            evoLeaf.ontouchstart = function(evoE){

                                    pon += 1;
                                var leafPickUp = new PickUpLeaf(evoE);
                                TanukiMove(tanuki,evoE);
                                evo_root = Evolution(evo_root,leaf_countR,leaf_countB);
                                parameter.frame = 5;
                                leaf_count = 0;
                                leaf_countR = 0;
                                leaf_countB = 0;
                                this.parentNode.removeChild(this);
                                EvolutionTanuki(tanuki,evoE);

                            };
                        scene.addChild(evoLeaf);
                        scene.addChild(evoTouch);
                        }
                    };
                    scene.addChild(leaf_groupR);
                    leaf_red[j] = null;
                    j++;
                }

            });

            //シーンのタッチイベント
            scene.on('touchstart', function(e){
                if(e.y >= 620　&& e.y <= 875){
                    TanukiMove(tanuki,e);
                }
            });

            return scene;
        };

        //エンディングシーンの作成
        var createEnddingScene = function(e){

            var scene = new Scene();

                //バックグラウンド
            var background = new Sprite(640,960);
              background.image = game.assets['./img/start_background.png'];
            scene.addChild(background);

            //コングラチュレーション画像
               var congratulations = new Sprite(601,111);
               congratulations.image = game.assets['./img/clear01.png'];
            congratulations.x = 15;
            congratulations.y = 260;
            congratulations.opacity = 0;
            congratulations.tl.fadeIn(192);
            //game.rootScene.addChild(congratulations);

            //バッテン画像
            var badend = new Sprite(392,412);
            badend.image = game.assets['./img/clear02.png'];
            badend.x = 120;
             badend.y = 100;
             badend.opacity = 0;
            badend.tl.fadeIn(240);
            //game.rootScene.addChild(badend);

            //ゲームオーバー画像
            var gameover = new Sprite(455,96);
            gameover.image = game.assets['./img/clear03.png'];
            gameover.x = 90;
            gameover.y = 260;
            //game.rootScene.addChild(gameover);


            //たぬきのスプライト初期設定
            var Tanuki = enchant.Class.create(enchant.Sprite, {
                initialize:function(w,h) {
                    enchant.Sprite.call(this,w,h);
                    this.tl.fadeIn(24);
                    this.opacity = 0;
                 this.x = 280;
                    this.y = 650;
                    this.frame = [2,2,2,1,1,1,2,2,2,0,0];
                    this.on('enterframe',function(){
                        this.opacity += 0.1;
                        if(this.opacity == 1){
                            this.opacity = 1;
                        }
                    });
                        }
            });

        //たぬきを作成と配列に追加
        //１進化
        var Tanukibox = new Array(16);

        Tanukibox[0] = new Tanuki(138,291);
        Tanukibox[0].image = game.assets['./img/1ude_1asi_1body_1face.png'];

        Tanukibox[1] = new Tanuki(136,275);
        Tanukibox[1].image = game.assets['./img/1ude_1asi_1body_2face.png'];

        Tanukibox[2] = new Tanuki(125,217);
        Tanukibox[2].image = game.assets['./img/1ude_1asi_2body_1face.png'];

        Tanukibox[3] = new Tanuki(126,216);
        Tanukibox[3].image = game.assets['./img/1ude_1asi_2body_2face.png'];

        Tanukibox[4] = new Tanuki(134,281);
        Tanukibox[4].image = game.assets['./img/1ude_2asi_1body_1face.png'];

        Tanukibox[5] = new Tanuki(137,268);
        Tanukibox[5].image = game.assets['./img/1ude_2asi_1body_2face.png'];

        Tanukibox[6] = new Tanuki(113,228);
        Tanukibox[6].image = game.assets['./img/1ude_2asi_2body_1face.png'];

        Tanukibox[7] = new Tanuki(114,224);
        Tanukibox[7].image = game.assets['./img/1ude_2asi_2body_2face.png'];

        //2進化
        Tanukibox[8] = new Tanuki(138,293);
        Tanukibox[8].image = game.assets['./img/2ude_1asi_1body_1face.png'];

        Tanukibox[9] = new Tanuki(140,277);
        Tanukibox[9].image = game.assets['./img/2ude_1asi_1body_2face.png'];

        Tanukibox[10] = new Tanuki(102,213);
        Tanukibox[10].image = game.assets['./img/2ude_1asi_2body_1face.png'];

        Tanukibox[11] = new Tanuki(102,212);
        Tanukibox[11].image = game.assets['./img/2ude_1asi_2body_2face.png'];

        Tanukibox[12] = new Tanuki(143,282);
        Tanukibox[12].image = game.assets['./img/2ude_2asi_1body_1face.png'];

        Tanukibox[13] = new Tanuki(144,276);
        Tanukibox[13].image = game.assets['./img/2ude_2asi_1body_2face.png'];

        Tanukibox[14] = new Tanuki(102,239);
        Tanukibox[14].image = game.assets['./img/2ude_2asi_2body_1face.png'];

        Tanukibox[15] = new Tanuki(93,222);
        Tanukibox[15].image = game.assets['./img/2ude_2asi_2body_2face.png'];



        //ラベルの配列設定
        var Label = enchant.Class.create(enchant.Label, {
            initialize:function() {
                enchant.Label.call(this);
                this.x = 70;
                this.y = 120;
                //this.textAling = 'center';
                this.color = 'white';
                this.width = '580';
                this.font = '28px sens-serif';
                this.height = 10;
                this.opacity = 0.7;
//                this.fps = 24;
                //this.visible = (78);

                }
        });

        //テキストの作成と配列に追加
        var Labelbox = new Array();

        Labelbox[0] = new Label();
        Labelbox[0].text = 'たぬきは完全な人間♂になりました。<br>男と言ったら筋肉だ。'
        +'という考えが染み付いていたたぬき君は筋肉マッチョなゴリゴリの人間になりました。<br>'
        +'もはや彼は人間のなかでも最強の部類なのでは、、、<br>人間界の生活に苦しむ中、彼はとうとう天職を見つけました。'
        +'それは、、<br>３年後、東京のとあるボディービルダーの大会にて謎の新人として大会に出場し、３位入賞を果たし、'
        +'その５ヶ月後には見事優勝を勝ち取り、現在はボディービルダー日本代表として頑張っているそうです。'
        +'彼がたぬきだったという事は絶対に秘密ですよ。<br>おめでとうございます。<br>完全な人間です!';

        Labelbox[1] = new Label();
        Labelbox[1].text = 'たぬきは筋肉ウーマンになりました。<br>顔を隠すと男の子、出すとプロレスラー。<br>'
        +'なぜこのようになってしまったのか。<br>それは、彼女は人間の男の身体しか見た事がなかったのです。'
        +'逞しい腕、麗しい足、美しい体を手に入れた彼女は顔の変身に迷いました。<br>そのとき、'
        +'偶然目にした森に落ちていた雑誌。その雑誌には初めて見る人間の顔と流行の口が載っていました。そうです。アヒル口です。'
        +'ですがどう見てもタラコ唇。<br>このようにして彼女はタラコ唇のプロレスラーになりました。人間界での生活は'
        +'なぜか周りの人たちが逃げていくようです。<br>おめでとうございます。<br>完全なプロレスラーの完成です。';

        Labelbox[2] = new Label();
        Labelbox[2].text = 'たぬきは変態になりました。<br> <br>どこから突っ込めばいいのでしょうか。<br> <br>'
        +'彼は何をどうしてこのように変身したんでしょうか。<br> <br>答えは誰にも分かりません。'
        +'<br> <br>彼は２足歩行より、４足歩行が似合っている気がします。<br> <br>'
        +'だが、本人は、「僕は人間だぽん！！」<br> <br>と言って聞かないそうです。<br> <br>おめでとうございます。<br>新人類の完成です。';

        Labelbox[3] = new Label();
        Labelbox[3].text = 'たぬきはよくわからなくなりました。<br> <br>'
        +'自分でもどうしてこのようになってしまったかは分からないみたいです。<br> <br>人間界におりてきた彼女は持ち前の筋肉で'
        +'水泳の選手になりました。<br> <br>何と、とある大会の女子200m自由形に出場し、犬かきで優勝してしまったそうです。<br> <br>'
        +'おめでとうございます。<br>ゴツゴツスイマーも完成です。';

        Labelbox[4] = new Label();
        Labelbox[4].text = 'たぬきは上半身筋トレマニアになりました。<br> <br>'
        +'彼は常に両腕に10キロの重りをつけて行動し、暇があれば腕立て伏せをしているそうです。<br>'
        +'おかげさまですばらしい上半身を手に入れました。<br> <br>ですが、、'
        +'下半身の筋トレはポリシーに反するそうでいっさい行わないみたいです。<br>面白い性格ですね。<br> <br>'
        +'おめでとうございます。<br>変な人の完成です。';

        Labelbox[5] = new Label();
        Labelbox[5].text = 'たぬきは女の子になりました。<br> <br>しかし体と手がムキムキなので、いつも長袖の服を着ています。<br>'
        +'ちなみに長袖は男物のLサイズです。<br> <br>でも足は美しいので常にミニスカです！！<br> <br>スカートはちゃんとした女物ですよ。<br> <br>'
        +'おめでとうございます。<br> <br>女の子?の完成です。';

        Labelbox[6] = new Label();
        Labelbox[6].text = 'たぬきは変態になりました。こう見えてとても優しく、とても男らしいのです。<br> <br>'
        +'マッチョな腕でどんなものでも潰す事が出来ます。<br>美しい足で他の女性達の目をかっさらう事が出来ます。'
        +'<br>要は、ちょっと個性的なのです。<br> <br>かわいいものには目がなく趣味はお人形集め。<br>'
        +'にたぬきの人形がお気に入りだそうです。<br> <br>おめでとうございます。<br> <br>心優しい変態の完成です。';

        Labelbox[7] = new Label();
        Labelbox[7].text = 'たぬきは重量挙げの世界的な選手になりました。オリンピックで金メダルを獲得し、'
        +'マスコミに追われる日々です。そんな彼女はある一人の男性から猛アタックされています。'
        +'うまくいきますように・・・';

        Labelbox[8] = new Label();
        Labelbox[8].text = 'たぬきはもう途中で気づいた。<br> <br>僕は既に間違えていると...<br> <br>'
        +'たぬきは道半ば必死に未来の容姿に対して活躍できる場を模索した。<br> <br>だが思い付かなかった。<br> <br>'
        +'いやいいんだよ。<br> <br>そういう仕様だしこうなっちゃうのもう仕方ないんだよね...<br> <br>たぬきは卑屈になった。';

        Labelbox[9] = new Label();
        Labelbox[9].text = '恥じらいが現れた。<br> <br>たぬきは「か細い腕で局部を隠す」を唱えた。<br> <br>恥じらいは10のダメージを受けた。<br> <br>'
        +'恥じらいは「成長」した。<br> <br>たぬきは999のダメージを受けた。<br> <br>たぬきは隠しきれなかった。<br> <br>たぬきは隠居した。';

        Labelbox[10] = new Label();
        Labelbox[10].text = 'たぬきはその体のくびれ具合に誇りを持っていた。<br> <br>実際、ものすごい曲線美でもあったため'
            +'彫像のモデルになった。<br> <br>その作品の美しさは未来永劫語られる事を本人は知らずタイで生きていくのであった。<br> <br>'
            +'ちなみにトイレは女子便ですよ。<br> <br>おめでとうございます。<br>名倉の完成です。';

            Labelbox[11] = new Label();
            Labelbox[11].text = 'たぬきは登山家になりました。<br> <br>たくましい足を使い、どんな山でもへっちゃらです。<br> <br>'
            +'なんとジャンプ力も凄まじく、その姿はウサギのようだそうです。<br>しょぼいですね。<br> <br>彼女は心はとっても純情で、'
            +'好きな人の前だと自分を出せないのが悩みだそうです。<br> <br>服装は常にジーパンと、割と足にコンプレックスを持っているそうですよ。<br> <br>'
            +'かわいいですね。<br> <br>おめでとうございます。<br> <br>洋梨の完成です。';

            Labelbox[12] = new Label();
            Labelbox[12].text = 'たぬきは貧弱な男性になりました。<br> <br>「男のくせにひょろひょろな手足！！やだ〜！きも〜い！！」'
            +'と、女性から貶されました。<br> <br>しかし、アッチ系の男性からはモテモテに。<br> <br>そう、彼はボーイズラブに目覚めてしまいました。<br> <br>'
            +'おめでとうございます。<br>どんだけ〜の完成です。';


            Labelbox[13] = new Label();
            Labelbox[13].text = 'たぬきは見た目は女の子になりました。<br> <br>服を着ているときはモテる女の子です。<br> <br>しかし・・・ '
            +'彼女は体だけ男なのです・・・<br> <br>人前で服は脱げません水着も着れません<br> <br>だから彼女はなかなか男性と突き合うことができません<br> <br>'
            +'モテモテなのに・・・<br> <br>おめでとうございます。<br>ギャップのある女性の完成です。';

            Labelbox[14] = new Label();
            Labelbox[14].text = 'たぬきはニューハーフになりました。<br> <br>見た目は女性、頭脳、、、間違えました、、顔は男性、'
            +'その名も、、新しい人種です。<br> <br>どうして顔は女性に変身しなかったのでしょうか。<br> <br>どうして服を着てないのでしょうか。'
            +'これは誰にも分からないのです。<br> <br>ただ、とってもかわいらしいすてきなニューハーフになれた事は真実です。<br> <br>'
            +'おめでとうございます。<br>すてきなNHになりました。';

               Labelbox[15] = new Label();
               Labelbox[15].text = 'たぬきは完全な人間（女）になりました。<br> <br>人間の女の子といったらナイスバディーだと思った彼女は'
            +'ボンキュッボンのすばらしいスタイルになりました。<br>口は流行のアヒル口、ショートカットなど、'
            +'細部にもこだわって変身しました。<br>なんてかわいいのでしょうか。<br>誰もたぬきだとは気づかないはずです。<br> <br>'
            +'数年後、彼女はパリコレに出場、優勝と凄まじい結果を出し世界的有名なファッションデザイナーに転身しました。<br> <br>'
            +'ですが、なぜか彼女がデザインしたものは茶色でフワフワなものが多いそうです。<br> <br>おめでとうございます。<br>完全な人間になりました。';

            Labelbox[16] = new Label();
            Labelbox[16].x = 70;
            Labelbox[16].y = 960;
            Labelbox[16].fps = 64;
            Labelbox[16].addEventListener('enterframe',function(){
                Labelbox[16].y--;
            Labelbox[16].text = '人生は常に選択です。<br>何事も選択である以上、どっちかを選ばなければなりません。'
            +'<br>そう考えると今のあなたはどうでしょうか。<br>赤の葉っぱと青の葉っぱをバランスよくとってみる。<br>'
            +'そんな事を考えていたら世の中うまく行きませんよ。<br>八方美人という言葉があるように'
            +'あなたの人生八方美人でいいのでしょうか。<br>いや、よくないはずです。<br>'
            +'これを機にあなたの人生が実り豊かになる事を祈っています。<br> <br>'
            +'残念ながらゲームオーバーです。<br> <br>   さようなら';
            });

            Labelbox[17] = new Label();
            Labelbox[17].x = 100;
            Labelbox[17].y = 960;
            Labelbox[17].fps = 64;
               Labelbox[17].addEventListener('enterframe',function(){
                Labelbox[17].y--;
            Labelbox[17].text = '制作者：Team.イエローズ<br> <br><担当><br> <br>デザイン兼リーダー　はたちゃん<br> <br>'
            +'コーディング　　　　どいちゃん<br>　　　　　　　　　　なかむー<br> <br><協力者><br> <br>丸源ひよこのメンターの方々<br> <br>'
            +'世界のみんな<素材提供><br> <br>うんたらかんたら<br> <br>'
            +'秦ちゃんから一言<br> <br>土井ちゃんから一言<br> <br>なかむーから一言<br> <br>以上。<br> <br>楽しんでいただきありがとうございます。';
            });

            var root = 0;
            root = e;

            if(root == 0 || root == 15){
                scene.addChild(Tanukibox[root]);
                scene.addChild(Labelbox[root]);
                scene.addChild(congratulations);
            }else if(root == 16){
                scene.addChild(gameover);
                scene.addChild(Labelbox[root]);
            }else{
                scene.addChild(Tanukibox[root]);
                scene.addChild(Labelbox[root]);
                scene.addChild(congratulations);
                scene.addChild(badend);
            }

            var t = 0;

            scene.on('enterframe',function(){

                t += 1/game.fps;

                if(t >= 10){
                    scene.ontouchstart = function(){



                        //初期化
//                           sound.stop();

                        //時間の初期化
                        time_l = 0;
                        time_t = 0;

                           //葉っぱの配列数
                        k = 0;
                        j = 0;

                        //        var bx = new Array(5);
                        //        var by = new Array(5);
                        //        var rx = new Array(5);
                        //        var ry = new Array(5);

                        //葉っぱの取得枚数
                        leaf_count = 0;

                        //葉っぱのスプライト保存
                            leaf_groupB = new Sprite();
                        leaf_groupR = new Sprite();
                        evoTouch = new Sprite();

                        //葉っぱの色フラグ
                        cFlag = 0;


                        //図鑑遷移ボタン選択
                        openClose = 1;
                        evo = 0;
                        pon = 0;
                        evo_root = 0;
                        evo_count = 0;
                        leaf_count = 0;
                        leaf_countR = 0;
                        leaf_countB = 0;
                        game.replaceScene(createTitleScene());
                        return;
                }
                }
            });



            return scene;
        }

        //タイトルシーンの挿入
        game.replaceScene(createTitleScene());
    };

    game.start();

};
