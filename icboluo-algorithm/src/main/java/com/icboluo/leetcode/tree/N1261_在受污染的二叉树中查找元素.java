package com.icboluo.leetcode.tree;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.icboluo.common.TreeNode;
import com.icboluo.util.I18nException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2024-07-26 上午8:34
 */
class N1261_在受污染的二叉树中查找元素 {
    TreeNode head;

    public N1261_在受污染的二叉树中查找元素(TreeNode root) {
        head = new TreeNode(0);
        dfs(root, head);
    }

    private void dfs(TreeNode root, TreeNode newRoot) {
        if (root == null) {
            return;
        }
        // 标准解法，但是并非最优，最优解法应该以set集合做容器
        if (root.left != null) {
            newRoot.left = new TreeNode(2 * newRoot.val + 1);
            dfs(root.left, newRoot.left);
        }
        if (root.right != null) {
            newRoot.right = new TreeNode(2 * newRoot.val + 2);
            dfs(root.right, newRoot.right);
        }
    }

    public boolean find(int target) {
        return dfs(head, target);
    }

    public boolean dfs(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        return root.val == target || dfs(root.left, target) || dfs(root.right, target);
    }

    public static void main(String[] args) {
        var cla = new N1261_在受污染的二叉树中查找元素(new TreeNode(-1, -1, -1, -1, -1));
        System.out.println(cla.find(1));
        System.out.println(cla.find(3));
        System.out.println(cla.find(5));

        // validBody
        public void invoke (T data, AnalysisContext context){
            // if只会执行一次
            if (msgList.isEmpty()) {
                isEmpty = false;
                for (int i = 0; i < excelEntity.headRowNumber; i++) {
                    msgList.add(new String[getMaxLine() + 1]);
                    rowMsgList.add(null);
                }
            }
            msgList.add(new String[getMaxLine() + 1]);
            Field[] fields = BeanUtil.getThisAndSupperDeclaredFields(excelEntity.clazz);
            Map<Field, String> msgMap = ValidateUtil.validateRow(data, fields);
            if (data instanceof RowValidate) {
                RowValidate rowValidate = (RowValidate) data;
                String rowError = rowValidate.afterFieldValidate();
                rowMsgList.add(rowError);
            } else {
                rowMsgList.add(null);
            }
            for (Map.Entry<Field, String> entry : msgMap.entrySet()) {
                Excel ep = entry.getKey().getAnnotation(Excel.class);
                msgList.get(msgList.size() - 1)[ep.columnIndex()] = entry.getValue();
            }
            if (msgMap.isEmpty() && !StringUtils.hasText(rowMsgList.get(rowMsgList.size() - 1))) {
                excelEntity.list.add(data);
            }
        }

/*                     msg.add(message);
}
            }
                if (StringUtils.hasText(rowMsgList.get(i))) {
                String rowMessage = MESSAGE_SOURCE.getMessage("row.{0}.error.{1}",
                new Object[] {i + 1, rowMsgList.get(i)}, LocaleContextHolder.getLocale());
                msg.add(rowMessage);
                }*/
        // headBody
        /**
         * 无参构造，cglib代理的时候使用，正常情况下不需要使用
         */
    }
}
