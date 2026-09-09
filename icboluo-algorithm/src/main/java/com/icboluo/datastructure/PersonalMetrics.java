package com.icboluo.datastructure;

import java.util.List;

/**
 * 个人身体、健身、体态与作息指标。
 * 单独成文件存放，方便大模型直接读取本人的个人数据。
 */
public class PersonalMetrics {

    /**
     * 本人档案：男 / 165cm / 66kg / 31岁
     */
    public static final PersonalProfile ME = new PersonalProfile(
            "男", 165, 66, 31,
            List.of(
                    new PersonalProfile.ExerciseRecord("屈腿（双腿）", "120 LB"),
                    new PersonalProfile.ExerciseRecord("哑铃推肩（单侧）", "8 KG"),
                    new PersonalProfile.ExerciseRecord("杠铃卧推（两侧）", "7.5 KG x 2 = 15 KG"),
                    new PersonalProfile.ExerciseRecord("硬拉（两侧）", "15 KG x 2 = 30 KG"),
                    new PersonalProfile.ExerciseRecord("反向蝴蝶机夹背", "27 LB"),
                    new PersonalProfile.ExerciseRecord("正向蝴蝶机夹胸", "32 LB"),
                    new PersonalProfile.ExerciseRecord("引体向上-正手", "3 个"),
                    new PersonalProfile.ExerciseRecord("引体向上-反手", "5 个"),
                    new PersonalProfile.ExerciseRecord("跑步机（配速 7.2）", "35 分钟，约 4.2 KG")
            ),
            List.of(
                    "圆肩驼背",
                    "骨盆前倾",
                    "小腹略突出",
                    "脖子前倾（探颈）",
                    "爱低头玩手机"
            ),
            List.of(
                    "晚上 1:30 睡觉",
                    "早上 8:10 起床",
                    "中午 1:20 休息 30 分钟",
                    "早饭一般不吃",
                    "午饭、晚饭饭量都比较大",
                    "上班久坐"
            )
    );

    public static void main(String[] args) {
        ME.print();
    }

    /**
     * 个人身体数据与健身指标，含 BMI 派生指标
     */
    public record PersonalProfile(String gender, int heightCm, int weightKg, int age,
                                  List<ExerciseRecord> exercises, List<String> postureIssues, List<String> lifeIssues) {

        public record ExerciseRecord(String name, String value) {
        }

        public void print() {
            double bmi = weightKg / Math.pow(heightCm / 100.0, 2);
            System.out.println("===== 个人身体与健身指标 =====");
            System.out.printf("性别：%s    年龄：%d    身高：%d cm    体重：%d kg%n", gender, age, heightCm, weightKg);
            System.out.printf("BMI：%.2f（%s）%n", bmi, bmiCategory(bmi));
            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-22s %-14s%n", "健身指标", "数值");
            System.out.println("-----------------------------------------------------------");
            for (ExerciseRecord e : exercises) {
                System.out.printf("%-24s %-14s%n", e.name(), e.value());
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("体态问题：");
            for (String issue : postureIssues) {
                System.out.printf("  - %s%n", issue);
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("生活作息问题：");
            for (String issue : lifeIssues) {
                System.out.printf("  - %s%n", issue);
            }
            System.out.println("-----------------------------------------------------------");
        }

        private static String bmiCategory(double bmi) {
            if (bmi < 18.5) {
                return "偏瘦";
            }
            if (bmi < 24) {
                return "正常";
            }
            if (bmi < 28) {
                return "偏重";
            }
            return "肥胖";
        }
    }
}
