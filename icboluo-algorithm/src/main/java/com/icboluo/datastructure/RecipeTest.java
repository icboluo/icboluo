package com.icboluo.datastructure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜谱工具：
 * 1. 列出总菜谱（已掌握 + 待学习）
 * 2. 根据现有的食材（蔬菜/肉蛋）匹配能做的菜，并生成一餐菜单
 *
 * @author icboluo
 * @since 2026-09-03
 */
class RecipeTest {

    /** 冰箱里现有的菜，改这里就行 */
    private static final List<String> FRIDGE = List.of("西红柿", "角瓜", "土豆", "青椒", "豆角", "香菇", "青菜", "蒜苔", "猪肉");

    /**
     * 我已掌握的菜（7 道）
     */
    private static final List<Recipe> RECIPES = List.of(
            new Recipe("西红柿炒鸡蛋", "素菜", "西红柿、鸡蛋", "盐、糖、葱花、食用油",
                    "鸡蛋加少许盐打散；热油炒蛋盛出；下西红柿炒出汁；倒回鸡蛋加盐糖翻匀撒葱花", 8,
                    "蛋嫩汁浓，酸甜汤汁最是下饭，新手也能零失败"),
            new Recipe("角瓜炒西红柿", "素菜", "角瓜、西红柿", "盐、生抽、蚝油、蒜、葱花",
                    "角瓜切片、西红柿切块；热油爆香蒜；下西红柿炒出汁；下角瓜片翻炒断生；加盐、生抽、蚝油翻匀撒葱花", 8,
                    "角瓜吸足番茄汤汁，脆嫩清爽又不出水，夏天快手素菜"),
            new Recipe("青椒土豆丝", "素菜", "土豆、青椒", "醋、盐、干辣椒、蒜",
                    "土豆切丝泡水去淀粉；热油爆香辣椒蒜；下土豆丝大火翻炒；加青椒丝、盐、醋翻匀", 10,
                    "脆爽酸辣、根根分明，考验刀工与大火快炒的功力"),
            new Recipe("干煸豆角", "素菜", "豆角、蒜", "盐、生抽、干辣椒、花椒",
                    "豆角掐段擦干；中小火煸至起皱；下辣椒花椒蒜；加盐生抽翻匀", 15,
                    "小火慢煸至起皱虎皮，干香微辣，豆角最香的做法"),
            new Recipe("香菇青菜", "素菜", "香菇、青菜", "盐、蚝油、蒜",
                    "香菇切片焯水；热油爆香蒜；下香菇和青菜翻炒；加盐蚝油出锅", 10,
                    "菌香与菜香交融，清淡解腻，摆盘好看的宴客素菜"),
            new Recipe("蒜苔炒肉", "荤菜", "蒜苔、猪肉", "生抽、盐、料酒、淀粉、干辣椒",
                    "肉丝腌制10分钟；滑炒盛出；下蒜苔炒断生；倒回肉丝加调料翻匀", 12,
                    "蒜苔脆嫩、肉丝滑嫩，咸鲜下饭的四季家常菜"),
            new Recipe("凉拌西红柿", "凉菜", "西红柿", "白糖",
                    "西红柿洗净切块；撒白糖拌匀冷藏10分钟", 5,
                    "不用开火，冰镇后酸甜开胃，夏天的解暑甜品菜")
    );

    /**
     * 待学习的菜，学会了把 Recipe.learning 改成 new Recipe 即可移入"已掌握"
     */
    private static final List<Recipe> LEARNING_RECIPES = List.of(
            Recipe.learning("醋溜白菜", "素菜", "白菜", "醋、生抽、盐、干辣椒、蒜",
                    "白菜帮斜刀切片；热油爆香蒜和干辣椒；下白菜大火翻炒；沿锅边淋醋生抽盐翻匀", 8,
                    "酸甜脆爽、锅气十足，最便宜的食材做出馆子味"),
            Recipe.learning("白菜炖粉条", "素菜", "白菜、粉条", "生抽、老抽、盐、八角",
                    "粉条温水泡软；白菜炒软；加水、粉条和调料；中小火炖10分钟收汁", 20,
                    "粉条吸饱汤汁，一锅出的北方家常炖菜，越炖越香"),
            Recipe.learning("手撕包菜", "素菜", "包菜", "醋、生抽、盐、干辣椒、蒜",
                    "包菜手撕成块洗净沥干；热油爆香蒜和干辣椒；下包菜大火快炒；加盐生抽醋翻匀", 8,
                    "手撕断面更易入味，大火爆炒锁住脆嫩与锅气"),
            Recipe.learning("蒜蓉西兰花", "素菜", "西兰花、蒜", "盐、蚝油、食用油",
                    "西兰花掰小朵焯水1分钟；热油爆香蒜末；下西兰花翻炒；加盐蚝油翻匀", 10,
                    "蒜香突出、色泽翠绿，焯水保色的清爽减脂菜"),
            Recipe.learning("清炒菠菜", "素菜", "菠菜", "盐、蒜、食用油",
                    "菠菜焯水去草酸；热油爆香蒜；下菠菜快炒加盐出锅", 6,
                    "快火断生保留脆嫩，最清爽的快手绿叶菜"),
            Recipe.learning("蒜蓉油麦菜", "素菜", "油麦菜、蒜", "盐、蚝油、食用油",
                    "油麦菜切段；热油爆香蒜；下油麦菜大火翻炒；加盐蚝油出锅", 6,
                    "蒜香浓郁、入口脆爽，几分钟出锅的下饭绿叶菜"),
            Recipe.learning("红烧茄子", "素菜", "茄子", "生抽、老抽、糖、盐、蒜、淀粉",
                    "茄子切块裹薄淀粉；煎至两面金黄；下蒜和调料汁；收汁撒葱花", 15,
                    "外焦里嫩、酱汁浓郁，裹粉煎制是不吸油的关键"),
            Recipe.learning("地三鲜", "素菜", "茄子、土豆、青椒", "生抽、蚝油、糖、盐、淀粉、蒜",
                    "茄子土豆切滚刀块；分别煎至金黄盛出；下青椒略炒；倒回茄子土豆，加调料汁翻炒收汁", 20,
                    "茄子土豆青椒三味合一，东北最下饭的素菜之王"),
            Recipe.learning("干锅花菜", "素菜", "花菜、猪肉", "蒜、干辣椒、生抽、盐、蚝油",
                    "花菜掰小朵焯水；五花肉片煸出油；下蒜辣椒爆香；下花菜大火翻炒；加调料出锅", 18,
                    "五花肉煸油提香，干香微辣、越嚼越香"),
            Recipe.learning("芹菜炒香干", "素菜", "芹菜、香干", "盐、生抽、蒜、干辣椒",
                    "芹菜切段焯水；香干切条；热油爆香蒜；下芹菜香干翻炒加调料", 10,
                    "脆嫩配豆香，清淡又有嚼头的素炒经典"),
            Recipe.learning("麻婆豆腐", "素菜", "豆腐、猪肉", "郫县豆瓣、花椒粉、生抽、盐、淀粉、蒜、葱花",
                    "豆腐切块盐水焯2分钟；肉末炒散；下豆瓣蒜末炒红油；加水煮开下豆腐；勾芡撒花椒粉和葱花", 18,
                    "麻、辣、烫、香、酥、嫩、鲜、活，川菜八字诀的代表"),
            Recipe.learning("拍黄瓜", "凉菜", "黄瓜、蒜", "醋、生抽、盐、香油",
                    "黄瓜拍裂切块；加蒜末和调料；拌匀冷藏5分钟", 6,
                    "拍裂断面更挂汁，蒜香酸爽的夏日凉菜头牌"),
            Recipe.learning("胡萝卜炒鸡蛋", "素菜", "胡萝卜、鸡蛋", "盐、葱花、食用油",
                    "胡萝卜切丝；鸡蛋打散炒熟盛出；下胡萝卜丝炒软；倒回鸡蛋加盐翻匀", 10,
                    "甜脆配嫩滑，颜色讨喜、孩子爱吃的营养快手菜"),
            Recipe.learning("洋葱炒鸡蛋", "素菜", "洋葱、鸡蛋", "盐、生抽、食用油",
                    "洋葱切丝；鸡蛋炒熟盛出；下洋葱炒软；倒回鸡蛋加盐生抽翻匀", 9,
                    "洋葱炒到回甜出香，最简单却最下饭的组合"),
            Recipe.learning("韭菜炒鸡蛋", "素菜", "韭菜、鸡蛋", "盐、食用油",
                    "韭菜切段；鸡蛋炒熟盛出；下韭菜快炒；倒回鸡蛋加盐翻匀", 8,
                    "春韭最香，鲜辣爽口的时令快手菜"),
            Recipe.learning("蒸鸡蛋羹", "蛋类", "鸡蛋", "盐、生抽、香油、温水",
                    "鸡蛋加1.5倍温水和盐打散过筛；盖保鲜膜扎孔；水开后中火蒸10分钟；淋生抽香油", 15,
                    "水蛋比例1:1.5，嫩如布丁、入口即化的国民蒸菜"),
            Recipe.learning("青椒炒肉", "荤菜", "青椒、猪肉", "生抽、老抽、盐、淀粉、料酒",
                    "肉片加料酒淀粉抓匀；滑炒至变色盛出；下青椒炒至虎皮；倒回肉片加调料翻匀", 12,
                    "虎皮青椒配滑嫩肉片，名副其实的米饭杀手"),
            Recipe.learning("莴笋炒肉片", "荤菜", "莴笋、猪肉", "盐、生抽、料酒、淀粉、蒜",
                    "莴笋切片；肉片腌制；滑炒肉片盛出；下莴笋炒断生；倒回肉片加调料翻匀", 12,
                    "莴笋脆爽清甜，荤素搭配最清爽的小炒"),
            Recipe.learning("鱼香肉丝", "荤菜", "猪肉、胡萝卜、木耳、青椒", "郫县豆瓣、糖、醋、生抽、淀粉、料酒",
                    "肉丝加料酒淀粉腌制；调鱼香汁（糖、醋、生抽、淀粉、水）；滑炒肉丝盛出；豆瓣炒出红油，下配菜炒断生；倒回肉丝淋鱼香汁收汁", 20,
                    "不见鱼却有鱼香，糖醋微辣、汁浓味厚的经典川味"),
            Recipe.learning("宫保鸡丁", "荤菜", "鸡胸肉、黄瓜、花生米", "干辣椒、花椒、糖、醋、生抽、淀粉、料酒",
                    "鸡丁腌制；调宫保汁；鸡丁滑炒盛出；爆香辣椒花椒；下黄瓜丁和鸡丁，淋汁翻炒；撒花生米", 20,
                    "糊辣酸甜、花生米酥脆，川菜的国际名片"),
            Recipe.learning("糖醋排骨", "荤菜", "排骨", "冰糖、醋、生抽、老抽、料酒、姜",
                    "排骨焯水；小火炒化冰糖下排骨上色；加调料和热水；中小火炖30分钟；大火收汁裹匀", 50,
                    "酸甜挂汁、色泽红亮，冷热皆宜的宴客硬菜"),
            Recipe.learning("可乐鸡翅", "荤菜", "鸡翅", "可乐、生抽、姜、料酒、盐",
                    "鸡翅两面划刀焯水；煎至两面金黄；倒入可乐没过鸡翅，加调料；中火焖15分钟大火收汁", 30,
                    "零门槛的甜口菜，色泽红亮、孩子最爱的下饭神器"),
            Recipe.learning("土豆炖牛肉", "荤菜", "土豆、牛肉", "生抽、老抽、料酒、八角、姜、盐",
                    "牛肉焯水；热油炒香姜和八角；下牛肉加调料炒上色；加热水炖40分钟；下土豆再炖20分钟", 70,
                    "牛肉酥烂、土豆绵软，汤汁拌饭一绝的炖菜"),
            Recipe.learning("紫菜蛋花汤", "汤羹", "紫菜、鸡蛋", "盐、香油、葱花",
                    "水烧开淋入蛋液画圈；下紫菜；加盐香油撒葱花", 8,
                    "几分钟出锅，清爽解腻的快手汤"),
            Recipe.learning("番茄土豆汤", "汤羹", "西红柿、土豆", "盐、葱花、香油",
                    "西红柿炒出汁；加土豆片和水；煮15分钟至土豆软；加盐撒葱花淋香油", 20,
                    "酸甜开胃、暖胃饱腹，一锅汤就是一顿饭"),
            Recipe.learning("冬瓜排骨汤", "汤羹", "冬瓜、排骨", "姜、盐、料酒",
                    "排骨焯水；加姜料酒炖40分钟；下冬瓜再炖15分钟；加盐出锅", 60,
                    "清甜解腻，夏秋最养人的家常慢炖汤"),
            Recipe.learning("酸菜鱼", "汤羹", "酸菜、鱼片", "泡椒、姜、蒜、料酒、淀粉、盐、花椒",
                    "鱼片加料酒淀粉腌制；酸菜炒干水汽；下泡椒姜蒜炒香；加水煮开下鱼片；烫熟后撒花椒淋热油", 35,
                    "酸辣开胃、鱼片滑嫩，川渝餐桌的顶流"),
            Recipe.learning("拔丝地瓜", "甜品", "地瓜", "白糖、食用油",
                    "地瓜切块炸至金黄；小火熬糖至琥珀色；下地瓜快速翻匀出锅", 25,
                    "糖丝能拉一米长，外脆内糯的宴席压轴甜品")
    );

    /**
     * 国宴名菜：多为功夫菜/仪式菜，默认"待学习"，仅作清单参考（不参与日常食材匹配）
     */
    private static final List<Recipe> BANQUET_RECIPES = List.of(
            Recipe.banquet("开水白菜", "川菜", "白菜、老母鸡、火腿、干贝", "盐、料酒、葱、姜",
                    "老母鸡火腿干贝吊清汤；鸡茸扫汤两遍至清澈见底；白菜取菜心焯熟；清汤浇入没过菜心", 150,
                    "汤色如水清澈见底，入口却鲜味醇厚；菜心清甜脆嫩，是「大味必淡」的极致"),
            Recipe.banquet("鸡豆花", "川菜", "鸡胸肉、鸡蛋清", "高汤、盐、淀粉、葱姜水",
                    "鸡胸捶茸去筋；加葱姜水、蛋清、淀粉搅成浆；高汤微沸时冲入鸡浆；小火凝成豆花状", 45,
                    "「吃鸡不见鸡」，鸡茸凝成豆花之形，清汤衬底，鲜嫩更胜豆腐"),
            Recipe.banquet("佛跳墙", "闽菜", "鲍鱼、海参、鱼唇、蹄筋、花菇、老母鸡", "绍兴酒、生抽、蚝油、姜、葱",
                    "食材分别焯水走油；层层码入酒坛；加绍兴酒和高汤，荷叶封口；小火煨6小时", 360,
                    "十八种山珍海味煨于一坛，「坛启荤香飘四邻，佛闻弃禅跳墙来」，闽菜之首"),
            Recipe.banquet("北京烤鸭", "京菜", "填鸭", "麦芽糖、葱白、黄瓜、甜面酱、薄饼",
                    "鸭胚充气、烫皮、淋糖水；晾坯8小时；果木挂炉烤50分钟；趁热片鸭，配葱丝酱和薄饼", 60,
                    "果木明炉烤制，皮酥肉嫩、油而不腻，讲究「一鸭三吃」、片鸭108片"),
            Recipe.banquet("松鼠鳜鱼", "苏菜", "鳜鱼", "番茄酱、糖、醋、盐、料酒、淀粉、松子",
                    "鳜鱼去骨剞花刀；拍干粉炸至定型如松鼠；另起锅熬番茄糖醋汁；浇汁时吱吱作响，撒松子", 30,
                    "花刀炸后形似松鼠、昂首翘尾，浇卤汁时「吱吱」有声，色红光亮、外脆里嫩"),
            Recipe.banquet("叫花鸡", "苏菜", "三黄鸡、猪网油、荷叶", "绍酒、生抽、盐、葱、姜、八角、黄泥",
                    "鸡腹塞调料腌制2小时；猪网油、荷叶层层包裹；外裹黄泥；煨烤4小时后敲泥开壳", 240,
                    "泥裹煨烤，敲开泥壳时荷叶清香扑鼻，鸡肉酥烂脱骨、原汁原味"),
            Recipe.banquet("蟹粉狮子头", "淮扬菜", "猪肉、蟹粉、荸荠", "盐、绍酒、葱姜水、淀粉、菜心",
                    "猪肋条细切粗斩成石榴粒；加蟹粉、荸荠摔打上劲；团成大丸；砂锅清汤炖90分钟", 100,
                    "细切粗斩、入口即化，肥而不腻、嫩如豆腐，淮扬「三头宴」之首"),
            Recipe.banquet("文思豆腐", "淮扬菜", "嫩豆腐、火腿、香菇、青菜", "高汤、盐、淀粉",
                    "嫩豆腐切五千根细丝；入清水散开；高汤调味勾薄芡；轻轻推入豆腐丝", 40,
                    "一块嫩豆腐横切成丝、细可穿针，考验极致刀工，入口即化"),
            Recipe.banquet("三套鸭", "淮扬菜", "家鸭、野鸭、鸽子", "火腿、香菇、冬笋、盐、绍酒、葱、姜",
                    "整鸭整鸽脱骨；鸽子套野鸭、野鸭套家鸭；腹填火腿香菇冬笋；砂锅炖3小时", 200,
                    "家鸭套野鸭、野鸭套鸽子，三禽合一，汤清味醇，淮扬功夫菜的代表"),
            Recipe.banquet("西湖醋鱼", "浙菜", "草鱼", "姜、醋、糖、酱油、料酒、淀粉",
                    "活鱼清水饿养吐泥；沸水汆熟不过油；调姜末糖醋汁勾芡；淋汁于鱼身", 20,
                    "现杀现烹、不见油星，酸甜中透出蟹肉般的鲜，源自「叔嫂传珍」的杭州名菜"),
            Recipe.banquet("东坡肉", "浙菜", "五花肉", "生抽、老抽、绍兴酒、冰糖、葱、姜",
                    "五花肉切方块焯水；葱姜垫底；加酒和酱油；小火焖90分钟再蒸30分钟", 120,
                    "「慢着火、少着水，火候足时它自美」，色泽红亮、酥烂如豆腐而形不碎"),
            Recipe.banquet("龙井虾仁", "浙菜", "虾仁、龙井茶", "蛋清、淀粉、盐、料酒、葱",
                    "虾仁上浆冷藏；龙井新茶85℃水泡开；滑炒虾仁至断生；下茶叶茶汁快翻出锅", 15,
                    "清明前后的龙井新茶配河虾仁，茶香清雅、虾仁玉白鲜嫩，清鲜不腻"),
            Recipe.banquet("葱烧海参", "鲁菜", "水发海参、大葱", "高汤、生抽、老抽、蚝油、糖、料酒、淀粉",
                    "大葱切段炸至金黄取葱油；海参焯水；葱油炒糖色；下海参高汤煨透；收汁勾芡", 40,
                    "葱香浓郁、海参软糯弹滑，「以葱烧参、以参提葱」，鲁菜海鲜头牌"),
            Recipe.banquet("九转大肠", "鲁菜", "猪大肠", "醋、糖、盐、酱油、料酒、砂仁、肉桂、胡椒、葱、姜、蒜",
                    "大肠套洗焯水；炸至定型；加十余种调料小火㸆；收汁时撒砂仁肉桂末", 90,
                    "酸、甜、苦、辣、咸五味俱全，层层递进如道家「九转金丹」"),
            Recipe.banquet("鲤鱼焙面", "豫菜", "黄河鲤鱼、龙须面", "糖、醋、酱油、盐、料酒、淀粉、葱、姜",
                    "鲤鱼剞瓦楞花刀；糖醋软熘至汁裹鱼身；龙须面炸至金黄盖于鱼上", 50,
                    "一菜两吃：糖醋软熘鲤鱼配细可穿针的焙面，「先食龙肉，后食龙须」")
    );

    /**
     * 家常菜总表 = 已掌握 + 待学习（参与日常食材匹配）
     */
    private static final List<Recipe> HOME_RECIPES = Stream.concat(RECIPES.stream(), LEARNING_RECIPES.stream()).toList();

    /**
     * 总菜谱 = 家常菜 + 国宴菜
     */
    private static final List<Recipe> ALL_RECIPES = Stream.concat(HOME_RECIPES.stream(), BANQUET_RECIPES.stream()).toList();

    /**
     * 家里常备、不用每次单独买的食材（算作默认拥有）
     */
    private static final Set<String> PANTRY = Set.of("鸡蛋", "蒜", "姜", "粉条", "紫菜", "香干");

    /**
     * 别名归一，避免"番茄/西红柿"这种同物异名匹配不上
     */
    private static final Map<String, String> ALIAS = Map.ofEntries(
            Map.entry("番茄", "西红柿"),
            Map.entry("圣女果", "西红柿"),
            Map.entry("马铃薯", "土豆"),
            Map.entry("洋白菜", "包菜"),
            Map.entry("卷心菜", "包菜"),
            Map.entry("甘蓝", "包菜"),
            Map.entry("大蒜", "蒜"),
            Map.entry("扁豆", "豆角"),
            Map.entry("四季豆", "豆角"),
            Map.entry("上海青", "青菜"),
            Map.entry("西葫芦", "角瓜")
    );

    // ==================== 1. 列出我会的菜 ====================

    @Test
    public void listMyDishes() {
        System.out.println("========== 总菜谱（共 " + ALL_RECIPES.size() + " 道） ==========");
        // 家常菜：按已掌握 / 待学习分组
        for (Level level : Level.values()) {
            List<Recipe> list = HOME_RECIPES.stream().filter(r -> r.level() == level).toList();
            System.out.println("【家常菜·" + level.text() + "】" + list.size() + " 道");
            printGroup(list);
        }
        // 国宴菜：按菜系分组，带特色说明
        System.out.println("【国宴名菜】" + BANQUET_RECIPES.size() + " 道（功夫菜，参考用）");
        printGroup(BANQUET_RECIPES);
        System.out.println("=========================================");
    }

    private static void printGroup(List<Recipe> list) {
        Map<String, List<Recipe>> group = list.stream()
                .collect(Collectors.groupingBy(Recipe::category, LinkedHashMap::new, Collectors.toList()));
        group.forEach((category, items) -> {
            System.out.println("  · " + category + "（" + items.size() + " 道）");
            for (Recipe recipe : items) {
                System.out.printf("      %-8s 主料：%-22s 约 %3d 分钟%n",
                        recipe.name(), recipe.ingredientText(), recipe.minutes());
                if (!recipe.feature().isBlank()) {
                    System.out.println("               特色：" + recipe.feature());
                }
            }
        });
    }

    // ==================== 2. 根据现有食材匹配 ====================

    @Test
    public void matchByVegetables() {
        List<RecipeMatch> matches = match(FRIDGE);
        System.out.println("========== 现有食材：" + String.join("、", normalize(FRIDGE)) + " ==========");

        List<RecipeMatch> cookable = matches.stream().filter(RecipeMatch::cookable).toList();
        System.out.println("【现在就能做】" + cookable.size() + " 道");
        if (cookable.isEmpty()) {
            System.out.println("  没有能直接做的菜，可以看看下面的采购建议");
        }
        for (int i = 0; i < cookable.size(); i++) {
            printRecipe(i + 1, cookable.get(i).recipe());
        }

        List<RecipeMatch> almost = matches.stream().filter(m -> !m.cookable() && m.missing().size() == 1).toList();
        System.out.println("【只差一样】" + almost.size() + " 道");
        for (RecipeMatch item : almost) {
            System.out.printf("  %-8s 还缺：%s%n", item.recipe().name(), item.missingText());
        }
        System.out.println("-----------------------------------------");
        System.out.println("采购清单（买回即可解锁）：" + String.join("、", shoppingList(almost)));

        // 待学习的菜：顺便看看现有食材能练哪些
        List<RecipeMatch> learning = match(FRIDGE, PANTRY, true).stream()
                .filter(m -> m.recipe().level() == Level.LEARNING)
                .toList();
        List<RecipeMatch> learnNow = learning.stream().filter(RecipeMatch::cookable).toList();
        System.out.println("【可以试着学】" + learnNow.size() + " 道");
        for (int i = 0; i < learnNow.size(); i++) {
            printRecipe(i + 1, learnNow.get(i).recipe());
        }
        List<RecipeMatch> learnAlmost = learning.stream().filter(m -> !m.cookable() && m.missing().size() == 1).toList();
        System.out.println("【学菜只差一样】" + learnAlmost.size() + " 道");
        for (RecipeMatch item : learnAlmost) {
            System.out.printf("  %-8s 还缺：%s%n", item.recipe().name(), item.missingText());
        }
        if (!learnAlmost.isEmpty()) {
            System.out.println("学菜采购清单：" + String.join("、", shoppingList(learnAlmost)));
        }
    }

    /**
     * 按现有食材匹配菜谱，默认叠加 {@link #PANTRY} 常备食材
     *
     * @param available 现有食材（蔬菜、肉蛋等）
     * @return 能做的排前面，其次按缺的食材数、耗时排序
     */
    private static List<RecipeMatch> match(Collection<String> available) {
        return match(available, PANTRY, false);
    }

    /**
     * 按现有食材匹配菜谱
     *
     * @param available       现有食材
     * @param pantry          常备食材（默认拥有，不参与"还缺"统计）
     * @param includeLearning 是否把"待学习"的菜也算进来
     */
    private static List<RecipeMatch> match(Collection<String> available, Collection<String> pantry, boolean includeLearning) {
        Set<String> stock = normalize(available);
        stock.addAll(normalize(pantry));
        // 国宴菜不参与日常食材匹配
        List<Recipe> source = includeLearning ? HOME_RECIPES : RECIPES;
        List<RecipeMatch> result = new ArrayList<>();
        for (Recipe recipe : source) {
            Set<String> used = new LinkedHashSet<>();
            Set<String> missing = new LinkedHashSet<>();
            for (String ingredient : recipe.ingredients()) {
                if (stock.contains(ingredient)) {
                    used.add(ingredient);
                } else {
                    missing.add(ingredient);
                }
            }
            result.add(new RecipeMatch(recipe, used, missing));
        }
        // 能做的排前面，其次按缺的食材数、耗时排序
        result.sort(Comparator
                .comparing((RecipeMatch m) -> !m.cookable())
                .thenComparingInt(m -> m.missing().size())
                .thenComparingInt(m -> m.recipe().minutes()));
        return result;
    }

    private static void printRecipe(int index, Recipe recipe) {
        String tag = recipe.level() == Level.LEARNING ? "【待学习】" : "";
        System.out.printf("  %d. %s%s（%s，约 %d 分钟）%n", index, recipe.name(), tag, recipe.category(), recipe.minutes());
        System.out.println("     主料：" + recipe.ingredientText());
        System.out.println("     调料：" + recipe.seasoningText());
        System.out.println("     做法：" + recipe.stepText());
        if (!recipe.feature().isBlank()) {
            System.out.println("     特色：" + recipe.feature());
        }
    }

    /**
     * "只差一样"的菜需要补买的食材清单
     */
    private static Set<String> shoppingList(List<RecipeMatch> almost) {
        Set<String> list = new LinkedHashSet<>();
        almost.forEach(m -> list.addAll(m.missing()));
        return list;
    }

    // ==================== 3. 生成一餐菜单 ====================

    @Test
    public void testMenu() {
        List<Recipe> menu = generateMenu(FRIDGE, 3);
        System.out.println("========== 推荐菜单（" + menu.size() + " 道） ==========");
        for (int i = 0; i < menu.size(); i++) {
            Recipe recipe = menu.get(i);
            System.out.printf("  %d. %-8s %-4s 约 %d 分钟%n", i + 1, recipe.name(), "【" + recipe.category() + "】", recipe.minutes());
            System.out.println("     做法：" + recipe.stepText());
        }
        Set<String> left = new LinkedHashSet<>(normalize(FRIDGE));
        menu.forEach(r -> left.removeAll(r.ingredients()));
        System.out.println("-----------------------------------------");
        System.out.println("剩余未用食材：" + (left.isEmpty() ? "无" : String.join("、", left)));
        System.out.printf("预计总耗时：%d 分钟%n", menu.stream().mapToInt(Recipe::minutes).sum());
    }

    /**
     * 根据现有食材生成菜单：优先选能消耗掉未使用食材的菜，尽量不重复主料
     *
     * @param available 现有食材
     * @param dishCount 要几个菜
     */
    private static List<Recipe> generateMenu(Collection<String> available, int dishCount) {
        Set<String> stock = normalize(available);
        stock.addAll(normalize(PANTRY));
        List<Recipe> cookable = match(available).stream().filter(RecipeMatch::cookable).map(RecipeMatch::recipe).toList();

        List<Recipe> menu = new ArrayList<>();
        Set<String> used = new LinkedHashSet<>();
        while (menu.size() < dishCount) {
            Recipe best = null;
            int bestScore = 0;
            for (Recipe recipe : cookable) {
                if (menu.contains(recipe)) {
                    continue;
                }
                // 能消耗掉还没用过的食材越多，优先级越高
                long fresh = recipe.ingredients().stream().filter(stock::contains).filter(i -> !used.contains(i)).count();
                if (fresh > bestScore) {
                    bestScore = (int) fresh;
                    best = recipe;
                }
            }
            if (best == null) {
                break;
            }
            menu.add(best);
            used.addAll(best.ingredients());
        }
        return menu;
    }

    /**
     * 名称归一：去空格 + 别名转换
     */
    private static Set<String> normalize(Collection<String> items) {
        if (items == null) {
            return new LinkedHashSet<>();
        }
        Set<String> result = new LinkedHashSet<>();
        for (String item : items) {
            if (item == null || item.isBlank()) {
                continue;
            }
            String name = item.trim();
            result.add(ALIAS.getOrDefault(name, name));
        }
        return result;
    }

    /**
     * 掌握程度：已掌握 / 待学习
     */
    public enum Level {
        MASTERED("已掌握"),
        LEARNING("待学习");

        private final String text;

        Level(String text) {
            this.text = text;
        }

        public String text() {
            return text;
        }
    }

    /**
     * 菜谱实体：一道菜的品类、主料、调料和做法
     */
    /** 系列：家常菜 */
    public static final String HOME_SERIES = "家常菜";
    /** 系列：国宴菜 */
    public static final String BANQUET_SERIES = "国宴菜";

    public record Recipe(String name,
                         String category,
                         Set<String> ingredients,
                         Set<String> seasonings,
                         List<String> steps,
                         int minutes,
                         Level level,
                         String series,
                         String feature) {

        public Recipe {
            level = level == null ? Level.MASTERED : level;
            series = series == null || series.isBlank() ? HOME_SERIES : series;
            feature = feature == null ? "" : feature;
            ingredients = ingredients == null ? Set.of() : Collections.unmodifiableSet(new LinkedHashSet<>(ingredients));
            seasonings = seasonings == null ? Set.of() : Collections.unmodifiableSet(new LinkedHashSet<>(seasonings));
            steps = steps == null ? List.of() : List.copyOf(steps);
        }

        /**
         * 便捷构造：主料/调料用顿号分隔，做法用分号分隔，默认"已掌握 + 家常菜"
         */
        public Recipe(String name, String category, String ingredients, String seasonings, String steps, int minutes) {
            this(name, category, splitToSet(ingredients), splitToSet(seasonings), splitToList(steps), minutes,
                    Level.MASTERED, HOME_SERIES, "");
        }

        /**
         * 便捷构造：带特色说明
         */
        public Recipe(String name, String category, String ingredients, String seasonings, String steps, int minutes, String feature) {
            this(name, category, splitToSet(ingredients), splitToSet(seasonings), splitToList(steps), minutes,
                    Level.MASTERED, HOME_SERIES, feature);
        }

        /**
         * 待学习的家常菜：学会后把这里改成 new Recipe(...) 即可移入"已掌握"
         */
        public static Recipe learning(String name, String category, String ingredients, String seasonings, String steps, int minutes) {
            return learning(name, category, ingredients, seasonings, steps, minutes, "");
        }

        /**
         * 待学习的家常菜：带特色说明
         */
        public static Recipe learning(String name, String category, String ingredients, String seasonings, String steps, int minutes, String feature) {
            return new Recipe(name, category, splitToSet(ingredients), splitToSet(seasonings), splitToList(steps), minutes,
                    Level.LEARNING, HOME_SERIES, feature);
        }

        /**
         * 国宴名菜：默认"待学习"，必须写特色说明
         */
        public static Recipe banquet(String name, String category, String ingredients, String seasonings, String steps, int minutes, String feature) {
            return new Recipe(name, category, splitToSet(ingredients), splitToSet(seasonings), splitToList(steps), minutes,
                    Level.LEARNING, BANQUET_SERIES, feature);
        }

        public boolean isBanquet() {
            return BANQUET_SERIES.equals(series);
        }

        public String ingredientText() {
            return String.join("、", ingredients);
        }

        public String seasoningText() {
            return String.join("、", seasonings);
        }

        public String stepText() {
            return String.join("；", steps);
        }

        private static Set<String> splitToSet(String text) {
            return new LinkedHashSet<>(splitToList(text));
        }

        private static List<String> splitToList(String text) {
            if (text == null || text.isBlank()) {
                return List.of();
            }
            return Arrays.stream(text.split("[、,，;；/]"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    /**
     * 一道菜的匹配结果
     *
     * @param recipe  菜谱
     * @param used    能用到的食材
     * @param missing 还缺的食材
     */
    public record RecipeMatch(Recipe recipe, Set<String> used, Set<String> missing) {

        public boolean cookable() {
            return missing.isEmpty();
        }

        public String missingText() {
            return String.join("、", missing);
        }
    }

    public static void main(String[] args) {
        new RecipeTest().listMyDishes();
        new RecipeTest().matchByVegetables();
        new RecipeTest().testMenu();
    }
}
