//
//  setQuetion.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/21.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "setQuetion.h"

@implementation setQuetion


NSString* const keasyTrue_string[30] =
    {@"アキ",
    @"先トウ",
    @"バイ収",
    @"ベニ色",
    @"建チク",
    @"水野タダ邦",
    @"火山バイ",
    @"ハリ",
    @"マズしい",
    @"過テイ",
    @"イモウト",
    @"ジン義",
    @"天守カク",
    @"ミる",
    @"カン単",
    @"身タイ",
    @"指キ",
    @"キサキ",
    @"クビ",
    @"お歳ボ",
    @"犬がナく",
    @"ミョウ朝",
    @"成セキ",
    @"シを詠む",
    @"シオが満ちる",
    @"シタ",
    @"ソク定",
    @"ソン害",
    @"タン任の先生",
    @"蛍セツの功",
    };
NSString* const keasyFalse_string[30][3] = {
    {@"ナツ",@"ハル",@"フユ"},
    {@"戦トウ",@"銭トウ",@"専トウ"},
    {@"バイ返し",@"バイ体",@"狼バイ"},
    {@"アオ色",@"ミドリ色",@"キ色"},
    {@"チク産",@"タてる",@"キズ付く"},
    {@"松平サダ信",@"チュウ国",@"宇チュウ"},
    {@"心パイ",@"祝ハイ",@"腐ハイ"},
    {@"トゲ",@"テツ",@"カネ"},
    {@"トボしい",@"ボン休み",@"気ヒン"},
    {@"家テイ",@"仮テイ",@"テイ操"},
    {@"アネ",@"メイ",@"ジョ性"},
    {@"ジン影",@"個ジン",@"初ジン"},
    {@"四カク形",@"原子カク",@"合カク"},
    {@"カンガみる",@"カン者",@"帰カン"},
    {@"カン嘆",@"朝カン",@"カン気"},
    {@"進タイ",@"タイ度",@"タイ与"},
    {@"四キ",@"元キ",@"軍キ"},
    {@"ヨメ",@"トツぐ",@"王ヒ"},
    {@"アタマ",@"ウデ",@"アシ"},
    {@"出席ボ",@"ボ音",@"ボ集"},
    {@"子供がナく",@"ナくなる",@"名もナい"},
    {@"余メイ",@"ミョウ利",@"メイ走"},
    {@"面セキ",@"セキ任",@"追セキ"},
    {@"シを迎える",@"音ドク",@"詩をヨむ"},
    {@"シオをまく",@"小チョウ",@"手チョウ"},
    {@"ウエ",@"ゼツ倫",@"ベロ"},
    {@"規ソク",@"反ソク",@"ソッ決"},
    {@"町ソン",@"ソン敬",@"子ソン"},
    {@"タン生日",@"一長一タン",@"タン純明快"},
    {@"セッ近戦",@"関セツ技",@"小セツ家"},
};
NSString* const kmediumTrue_string[30] =
{
    @"稲ホ",
    @"地ジク",
    @"おジョウ様",
    @"ジュ教",
    @"ヌマ",
    @"モれる",
    @"クダく",
    @"モヨオす",
    @"セン人",
    @"シャーシン",
    @"シカる",
    @"スイ飯器",
    @"ゼン次",
    @"海ゾク",
    @"ジュン環器",
    @"イラ立ち",
    @"管カツ",
    @"イコいの場",
    @"サトる",
    @"水ショウ体",
    @"面目躍ジョ",
    @"シバラく",
    @"クサリ",
    @"頓ザ",
    @"コッ己心",
    @"結コン",
    @"セッ取",
    @"ウレい",
    @"アゴ",
    @"憧ケイ",
};
NSString* const kmediumFalse_string[30][3] =
{
    {@"ホ装",@"ホ習",@"ホ獲"},
    {@"天ジク",@"ヤワらかい",@"ユ出"},
    {@"ジョウ渡する",@"メイ",@"シュウトメ"},
    {@"ジュ要",@"ジュ命",@"ジュ文"},
    {@"ドロ",@"雲デイ",@"イケ"},
    {@"タれる",@"シみ込む",@"タまる"},
    {@"コワす",@"異サイ",@"火サイ"},
    {@"負サイ",@"書サイ",@"アナドる"},
    {@"セン任",@"セン水",@"新セン"},
    {@"秒シン",@"興味シンシン",@"口シン"},
    {@"サケぶ",@"タタく",@"ホえる"},
    {@"三角スイ",@"スイ晶体",@"スイ奏楽"},
    {@"アタカも",@"ザン像",@"キる"},
    {@"付ゾク",@"世ゾク",@"家ゾク"},
    {@"従ジュン",@"矛ジュン",@"ジュン正"},
    {@"イキドオり",@"カ能性",@"カけ橋"},
    {@"カッ走",@"カツ愛",@"カッ発"},
    {@"マドロむ",@"ケイ谷",@"半ケイ"},
    {@"サトす",@"看ゴ",@"ゴ石"},
    {@"暗ショウ",@"銅ショウ",@"ショウ諾"},
    {@"マツリゴト",@"コトゴトく",@"ニョ人"},
    {@"ザン党狩り",@"アタカも",@"サン上する"},
    {@"ナマリ",@"検サ",@"発サ"},
    {@"前ザ",@"ザ禅",@"スワる"},
    {@"コッ稽",@"カッ走",@"残コク"},
    {@"コン立",@"血コン",@"コン親会"},
    {@"セツ約",@"セツ那",@"骨セツ"},
    {@"イコいの場",@"シュウ逸",@"一シュウ"},
    {@"ホホ",@"驚ガク",@"豊キョウ"},
    {@"ケ色",@"烏骨ケイ",@"処ケイ"},
};

NSString* const khardTrue_string[30] =
{
    @"アイ昧",
    @"山アラシ",
    @"イ度",
    @"シオれる",
    @"イバラ城県",
    @"ノド",
    @"イ子",
    @"カジカ",
    @"明リョウ",
    @"ラ刹",
    @"脈ラク",
    @"空ラン",
    @"ズボンをハく",
    @"リン理",
    @"戦リツ",
    @"伴リョ",
    @"トリコ",
    @"暗ヤミ",
    @"ホトトギス",
    @"ウグイス",
    @"モズ",
    @"サギ",
    @"トキ",
    @"フクロウ",
    @"タコ",
    @"タラ",
    @"マグロ",
    @"ナマズ",
    @"スルメ",
    @"ノノシる",
};
NSString* const khardFalse_string[30][3] = {
    {@"アイ愁",@"アイ色",@"アイ手"},
    {@"吹きスサぶ",@"閲ラン",@"狂ラン"},
    {@"萎シュク",@"コン色",@"イ人"},
    {@"カれる",@"明治イ新",@"イ員会"},
    {@"ムチ",@"バラの棘",@"トチ木県"},
    {@"婚イン届",@"イン気",@"口クウ"},
    {@"ツクエ",@"サク",@"物干サオ"},
    {@"タイ",@"アユ",@"アシカ"},
    {@"リョウ生活",@"同リョウ",@"西リョウ"},
    {@"ラ旋",@"ラ体",@"ラ致"},
    {@"ラク農",@"堕ラク",@"ラク天"},
    {@"氾ラン",@"ラン学",@"ラン蝶"},
    {@"本音をハく",@"庭をハく",@"狐リ"},
    {@"サトす",@"一リンの花",@"ロン理的"},
    {@"旋リツ",@"クリ",@"倒リツ"},
    {@"お風ロ",@"ロ西亜",@"暖ロ"},
    {@"オモンパカる",@"トラ",@"遠リョ"},
    {@"ヤみ上がり",@"イン蔽",@"オン便"},
    {@"ウズラ",@"キジ",@"ガン"},
    {@"カモメ",@"タカ",@"サギ"},
    {@"スズメ",@"クグイ",@"シギ"},
    {@"シャモ",@"ツバメ",@"ツル"},
    {@"トビ",@"ハト",@"ハヤブサ"},
    {@"ヌエ",@"ニワトリ",@"ヒバリ"},
    {@"イカ",@"カレイ",@"スルメ"},
    {@"サケ",@"タイ",@"フグ"},
    {@"ワカサギ",@"タナゴ",@"サワラ"},
    {@"クジラ",@"カツオ",@"サバ"},
    {@"イカ",@"カレイ",@"スズキ"},
    {@"ソシる",@"ラ旋",@"バ頭"},
};



//問題の画像格納
+ (NSMutableArray*)quetionBox:(int)difficultyTag {
    
    NSMutableArray* qBox = [NSMutableArray array];
    if (difficultyTag == 0) {
        for (int i = 0; i < 30; i++) {
            
            
            NSString* imageStr = [NSString stringWithFormat:@"%d.png",i];
            [qBox addObject:imageStr];
        }
    } else if (difficultyTag == 1) {
        for (int i = 100; i < 130; i++) {
            NSString* imageStr = [NSString stringWithFormat:@"%d.png",i];
            [qBox addObject:imageStr];

        }
    } else if (difficultyTag == 2) {
        for (int i = 200; i < 230; i++) {
            NSString* imageStr = [NSString stringWithFormat:@"%d.png",i];
            [qBox addObject:imageStr];
        }
    }
    
    return qBox;
    
}

//問題の選択肢格納
+ (NSMutableDictionary*)selectBtnTitle:(int)difficultyTag quetionNumber:(int)number {
    
    
    NSMutableDictionary* selectBox = [NSMutableDictionary dictionary];
    
    if (difficultyTag == 0) {
        [selectBox setObject:keasyTrue_string[number] forKey:@"TRUE"];
        for (int i = 0; i<3 ; i++) {
            [selectBox setObject:keasyFalse_string[number][i] forKey:[NSString stringWithFormat:@"False%d",i]];
        }
    } else if (difficultyTag == 1){
        [selectBox setObject:kmediumTrue_string[number] forKey:@"TRUE"];
        for (int i = 0; i<3 ; i++) {
            [selectBox setObject:kmediumFalse_string[number][i] forKey:[NSString stringWithFormat:@"False%d",i]];
        }
    } else if (difficultyTag == 2) {
        [selectBox setObject:khardTrue_string[number] forKey:@"TRUE"];
        for (int i = 0; i<3 ; i++) {
            [selectBox setObject:khardFalse_string[number][i] forKey:[NSString stringWithFormat:@"False%d",i]];
        }
    }
    
    return selectBox;
    
}

//選択肢のランダムソート
+ (NSMutableArray*)randomSelect:(NSMutableDictionary*)selectStringDictionary {
    
    NSArray* array = (NSMutableArray*)[selectStringDictionary allKeys];
    NSMutableArray* selectTitle = [NSMutableArray arrayWithArray:array];
    
    for (int i = 0; i<50; i++) {
        int src = random() % 4;
        int dst = src;
        while (dst == src) {
            dst = random() % 4;
    }
        [selectTitle exchangeObjectAtIndex:src withObjectAtIndex:dst];
    }
    
    return selectTitle;
}



@end
