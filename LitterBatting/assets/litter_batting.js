/*global window, event,clearInterval,localStorage,Game,Class,Scene,Sprite,enchant,Play,PlayScene,Label,RecordScene,GameOverScene,Event,TitleScene,RecordsScene,andjs,document,$*/

enchant();

window.onload = function() {
    //ゲームオブジェクトの生成
    var game = new Game(980, 640);
    //1秒間のコマ数
    game.fps = 20;
    //画像の読み込み
    game.preload('img/background_start.png','img/background_title.png',
                 'img/background.png', 'img/batting_chara.png',
                 'img/button_apps.png',
                 'img/button_go.png', 'img/button_replay.png',
                 'img/can.png','img/can_red.png','img/clean_up_boy.png',
                 'img/paper.png','img/sorry.png','img/tabacco.png',
                 'img/target.png','img/what_are_you_doing.png', 'img/point.png');
    //    //サウンドの読み込み
    //    sound = Sound.load();
    
    game.onload = function() {
        var restNumber = 20;
        var scoreCount = 0;
        
        TitleScene = Class.create(Scene, {
            initialize:function() {
                Scene.call(this);
                var Background = new Sprite(980, 640);//タイトル背景画像
                Background.image = game.assets['img/background_title.png'];
                Background.y = -60;
                this.addChild(Background);
                var GoButton = new Sprite(274, 154);//goボタン
                GoButton.image = game.assets['img/button_go.png'];
                GoButton.x = 300;
                GoButton.y = 230;
                this.addChild(GoButton);
                GoButton.addEventListener(enchant.Event.TOUCH_END, function() {
//                    andjs.hideAd();
                    Play = new PlayScene();
                    game.replaceScene(Play); // ゲームプレイ画面に遷移
                });
            }
        });
        
        
        PlayScene = Class.create(Scene, {
            initialize:function() {
                Scene.call(this);
                //ゲームプレイ背景画像
                var Background3 = new Sprite(980, 640);
                Background3.image = game.assets['img/background.png'];
                Background3.y = -60;
                this.addChild(Background3);
                // 残数のマーク
                var garbageSynbol = new Sprite(80, 80);
                garbageSynbol.image = game.assets['img/can_red.png'];
                garbageSynbol.x = 10;
                garbageSynbol.y = 15;
                this.addChild(garbageSynbol);
                //残数表示
                var RestNumberLabel = new Label("×" + restNumber);
                RestNumberLabel.color = 'white';
                RestNumberLabel.font = "50px monospace";
                RestNumberLabel.x = 90;
                RestNumberLabel.y = 20;
                this.addChild(RestNumberLabel);
                // スコアのマーク
                var scoreSynbol = new Sprite(80, 80);
                scoreSynbol.image = game.assets['img/point.png'];
                scoreSynbol.x = 10;
                scoreSynbol.y = 90;
                this.addChild(scoreSynbol);
                //スコア表示
                var ScoreLabel = new Label(":" + scoreCount);
                ScoreLabel.color = 'white';
                ScoreLabel.font = "50px monospace";
                ScoreLabel.x = 90;
                ScoreLabel.y = 95;
                this.addChild(ScoreLabel);
                //遊び方の説明
                var HowToPlayLabel = new Label("タイミングよくタップしてゴミを打て！");
                HowToPlayLabel.color = 'black';
                HowToPlayLabel.font = "30px monospace";
                HowToPlayLabel.x = 100;
                HowToPlayLabel.y = 160;
                HowToPlayLabel.height = 300;
                HowToPlayLabel.width = 600;
                this.addChild(HowToPlayLabel);
                //バッティングキャラ
                var player = new Sprite(525,700);
                player.image = game.assets['img/batting_chara.png'];
                player.x = 460;
                player.y = 0;
                player.tick = 0;
                player.anim = [0,0,1,1,2,2,3,3,4,4,5,5,
                               6,6,6,6,6,6,6,6,6,null];
                player.isFirst = true;
                this.addChild(player);
                //バットの的
                var target = new Sprite(50,50);
                target.x = 790;
                target.y = 85;
                target.animx = [790,790,755,755,780,780,
                                820,820,650,650,530,530,
                                820,820,820,820,820,820,820,820,820,null];
                target.animy = [45,45,95,95,120,120,
                                160,160,290,290,200,200,null,
                                200,200,200,200,200,200,200,200,200,null];
                this.addChild(target);
                var target2 = new Sprite(70,70);
                target2.image = game.assets['img/target.png'];
                target2.x = 530;
                target2.y = 200;
                this.addChild(target2);
                //飛んでくるゴミ
                var litter = new Sprite(80,80);
                var litter_img = ['img/can_red.png','img/can.png',
                                  'img/paper.png','img/tabacco.png'];
//                i = Math.floor(Math.random() * 4);
                litter.image = game.assets[litter_img[Math.floor(Math.random() * 4)]];
                litter.x = -30;
                litter.x_step = 30;
                litter.y = 220;
                litter.y_prev = 240;
                litter.canStartThrow = false;
                this.addChild(litter);
                //吹き出しエフェクト
                var effect1 = new Sprite(218,132);
                effect1.image = game.assets['img/sorry.png'];
                effect1.x = 20;
                effect1.y = 280;
                var effect2 = new Sprite(218,132);
                effect2.image = game.assets['img/what_are_you_doing.png'];
                effect1.x = 20;
                effect2.y = 160;
                //タッチスタート
                var TouchStart = new Sprite(980, 640);
                TouchStart.image = game.assets['img/background_start.png'];
                this.addChild(TouchStart);
                
                TouchStart.addEventListener(enchant.Event.TOUCH_END, function() {
                    Play.removeChild(TouchStart);
                    //ゴミを飛ばす処理
                    litter.addEventListener(Event.ENTER_FRAME, function() {
                        litter.x += litter.x_step;
                        // ベル法を使う
                        var y_temp = this.y;
                        this.y += (this.y- this.y_prev) + 2;
                        this.y_prev = y_temp;
                        
                        if (this.x >= 1000) {
                            this.x = -30;
                            this.y = 220;
                            this.y_prev = 240;
                            Play.removeChild(effect1);
                            Play.removeChild(effect2);
                            restNumber--;
                            RestNumberLabel.text = "×" + restNumber;
                            litter.image = game.assets[litter_img[Math.floor(Math.random() * 4)]];
                        }
                        if (litter.x <= -31) {
                            litter.x = -30;
                            litter.x_step = 30;
                            litter.y = 220;
                            litter.y_prev = 240;
                            restNumber--;
                            RestNumberLabel.text = "×" + restNumber;
                            litter.image = game.assets[litter_img[Math.floor(Math.random() * 4)]];
                        }
                        if (restNumber <= 15) {
                            Play.removeChild(HowToPlayLabel);
                        }
                        
                        //ゲームオーバー画面に遷移
                        if (restNumber <= 0) {
                            Play.removeChild(litter);
                            Play.removeChild(target2);
                            var GameOver = new GameOverScene();
                            GameOver.backgroundColor = "rgba(128,128,128,0.7)";
                            game.pushScene(GameOver);
                        }
                    });
                    //バットを振る
                    player.addEventListener(Event.TOUCH_START,function() {
                        this.tick = 0;
                        this.isFirst = false;
                        //                var sound = game.assets['hey_you.mp3'];//バットを振ったときの効果音
                        //                sound.play();
                    });
                    player.addEventListener(Event.ENTER_FRAME, function() {
                        if (!this.isFirst) {
                            this.frame = this.anim[this.tick];
                            this.tick++;
                        }
                        //衝突判定
                        if (target.within(litter,50)) {
                            litter.x_step = -80;
                            litter.y_prev += 50;
                            Play.removeChild(effect1);
                            Play.removeChild(effect2);
                            Play.addChild(effect2);
                            scoreCount += 50;
                            ScoreLabel.text = ":" + scoreCount;
                            if (target.within(litter,25)) {
                                litter.x_step = -80;
                                litter.y_prev += 50;
                                Play.removeChild(effect2);
                                Play.addChild(effect1);
                                scoreCount += 50;
                                ScoreLabel.text = ":" + scoreCount;
                            }
                        }
                    });
                    //スウィングと一緒に的が動く処理
                    target.addEventListener(Event.ENTER_FRAME, function() {
                        if (!player.isFirst) {
                            this.x = this.animx[player.tick];
                            this.y = this.animy[player.tick];
                        }
                    });
                });
                // localStorage.clear();　// ←ローカルストレージのデータを全て消去
                // 初期値の入力
                if (localStorage.getItem('No1_Score') === null) {
                    localStorage.setItem('No1_Score', 0);
                    localStorage.setItem('No2_Score', 0);
                    localStorage.setItem('No3_Score', 0);
                    localStorage.setItem('No4_Score', 0);
                    localStorage.setItem('No5_Score', 0);
                }
            }
        });
        GameOverScene = Class.create(Scene, {
            initialize:function() {
                Scene.call(this);
                var GameOverLabel = new Label("FINISH");
                GameOverLabel.color = 'green';
                GameOverLabel.font = "120px monospace";
                GameOverLabel.x = 300;
                GameOverLabel.y = 210;
                GameOverLabel.height = 300;
                GameOverLabel.width = 600;
                this.addChild(GameOverLabel);
                this.tick = 0;
                this.addEventListener(Event.ENTER_FRAME, function() {
                    if (this.tick > 60) {
//                        andjs.showAd();
                        var Recoreds = new RecordsScene();
                        game.pushScene(Recoreds);
                    }
                    this.tick++;
                });
            }
        });
        RecordsScene = Class.create(Scene, {
            initialize:function() {
                Scene.call(this);
                var Background4 = new Sprite(980, 640);//ゲームプレイ背景画像
                Background4.image = game.assets['img/background.png'];
                Background4.y = -60;
                this.addChild(Background4);
                var RecordsTitle = new Label("Records");//残数表示
                RecordsTitle.color = 'black';
                RecordsTitle.font = "50px monospace";
                RecordsTitle.x = 20;
                RecordsTitle.y = 30;
                this.addChild(RecordsTitle);
                var CleanUpBoy = new Sprite(555, 666);//ゴミ拾いボーイ
                CleanUpBoy.image = game.assets['img/clean_up_boy.png'];
                CleanUpBoy.x = 420;
                CleanUpBoy.y = -100;
                CleanUpBoy.scaleX = 0.7;
                CleanUpBoy.scaleY = 0.7;
                this.addChild(CleanUpBoy);
                var BackScreen = new Sprite(480,310);
                BackScreen.backgroundColor = 'white';
                BackScreen.x = 50;
                BackScreen.y = 100;
                this.addChild(BackScreen);
                
                var strageData = [];
                var keyOfStrage = ['No1_Score', 'No2_Score', 'No3_Score', 'No4_Score', 'No5_Score'];
                var scoreRenewal = null;
                // 配列にローカルストレージから取得したデータをいれる
                for (var m = 0 ; m < localStorage.length ; m++) {
                    // 数値に置き換えて配列に追加
                    strageData.push(parseInt(localStorage.getItem(keyOfStrage[m])));
                }
                function desc(a,b){
                    return (b-a);
                }
                // ローカルストレージの更新
                for (var i = 0 ; i < localStorage.length ; i++) {
                    // 現在のスコアがランキング圏内か調べる
                    if (scoreCount >= strageData[i]) {
                        strageData.push(scoreCount);
                        
                        // strageDataを降順に並び替え，並び替えたものをストレージに保存
                        desc();
                        strageData.sort(desc);
                        // ローカルストレージの更新
                        for (var j = 0 ; j < localStorage.length ; j++) {
                            localStorage.setItem(keyOfStrage[j], strageData[j]);
                        }
                        // ランキング表示の更新のため
                        scoreRenewal = i;
                        break;
                    }
                }
                
                var ranking = [];
                for (var k = 0 ; k < 5 ; k++) {
                    ranking[k] = new Label();
                    ranking[k].x = 110;
                    ranking[k].y = 100 + 60 * k;
                    ranking[k].font = '50px Time New Roman';
                    ranking[k].text =  k+1 + '位';
                    ranking[k].color = '#373737';
                    this.addChild(ranking[k]);
                }
                
                var rankScore = [];
                for (var l = 0 ; l < 5 ; l++) {
                    rankScore[l] = new Label();
                    rankScore[l].x = 350;
                    rankScore[l].y = 100 + 60 * l;
                    rankScore[l].font = '50px Time New Roman';
                    // 更新されたスコアを赤文字にするため
                    if (l === scoreRenewal) {
                        rankScore[l].text = localStorage.getItem(keyOfStrage[l]);
                        rankScore[l].color = 'red';
                    } else {
                        rankScore[l].text = localStorage.getItem(keyOfStrage[l]);
                        rankScore[l].color = '#373737';
                    }
                    this.addChild(rankScore[l]);
                }
                // 今のスコア
                var ScoreNow = new Label();
                ScoreNow.x = 250;
                ScoreNow.y = 30;
                //ScoreNow._element.style.textAlign = 'center';
                ScoreNow.color ='#000';
                ScoreNow.font = '50px Time New Roman';
                ScoreNow.text = scoreCount + 'Point';
                this.addChild(ScoreNow);
                // ScoreNowを初期化
                scoreCount = 0;
                
                this.tick  = 0;
                this.addEventListener(Event.ENTER_FRAME, function() {
                    // 現在のスコアを点滅させる
                    ScoreNow.opacity = (Math.cos((this.tick*20) * Math.PI/180) + 0.5);
                    this.tick++;
                });
                //リリース後にAppsボタンを付ける
                var AppsButton = new Sprite(150, 82);//Appsボタン
                AppsButton.image = game.assets['img/button_apps.png'];
                AppsButton.x = 650;
                AppsButton.y = 320;
                this.addChild(AppsButton);
                AppsButton.addEventListener(enchant.Event.TOUCH_END, function() {
//                    andjs.cutinAd();
                });
                
                var ReplayButton = new Sprite(150, 82);//Replayボタン
                ReplayButton.image = game.assets['img/button_replay.png'];
                ReplayButton.x = 800;
                ReplayButton.y = 320;
                this.addChild(ReplayButton);
                ReplayButton.addEventListener(enchant.Event.TOUCH_END, function() {
//                    andjs.hideAd();
                    restNumber = 20;
                    Play = new PlayScene();
                    game.pushScene(Play); // スタート画面に遷移
                });
            }
        });
        
        //タイトル画面へ遷移
        var Title = new TitleScene(this);
        game.pushScene(Title);
        
    };
    game.start();
    /////////////////////////////////////////////////
};
