package com.ss.tools;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


public class TreeUtils {

    public static <T extends ITNode> List<TNode<T>> create(List<T> list) {
        Queue<TNode<T>> queue = createQueue(list);
        List<TNode<T>> tree = new ArrayList<TNode<T>>();

        while (!queue.isEmpty()) {


            TNode<T> node = (TNode) queue.poll();
            if (isTopNode(node) || !hasParent(list, node)) {
                node.setLevel(Integer.valueOf(1));
                tree.add(node);


                continue;
            }

            TNode<T> parent = query(tree, node.getParentId());
            if (parent != null) {

                List<TNode<T>> children = parent.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    parent.setLeaf(false);
                }

                node.setLevel(Integer.valueOf(parent.getLevel().intValue() + 1));
                children.add(node);

                continue;
            }

            queue.add(node);
        }


        return tree;
    }


    private static <T extends ITNode> Queue<TNode<T>> createQueue(List<T> list) {
        Queue<TNode<T>> queue = new ArrayDeque<TNode<T>>();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            T item = (T) (ITNode) iterator.next();
            queue.add(new TNode(item));
        }


        return queue;
    }


    public static boolean isTopNode(ITNode node) {
        return !StringUtils.hasText(node.getParentId());
    }


    public static boolean hasParent(List<? extends ITNode> list, ITNode node) {
        if (!CollectionUtils.isEmpty(list)) {
            for (ITNode t : list) {
                if (t.getId().equals(node.getParentId())) {
                    return true;
                }
            }
        }

        return false;
    }


    public static <T extends ITNode> TNode<T> query(List<TNode<T>> list, String id) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }


        Stack<TNode<T>> stack = new Stack<TNode<T>>();
        stack.addAll(list);

        while (!stack.isEmpty()) {


            TNode<T> node = (TNode) stack.pop();
            if (node.getId().equals(id)) {
                return node;
            }


            List<TNode<T>> children = node.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                stack.addAll(children);
            }
        }

        return null;
    }

}
