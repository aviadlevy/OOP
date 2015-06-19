package oop.ex5.data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class BTreePrinter {

	public static void printNode(AvlNode root1) {
		int maxLevel = BTreePrinter.maxLevel(root1);

		printNodeInternal(Collections.singletonList(root1), 1, maxLevel);
	}

	private static void printNodeInternal(List<AvlNode> list, int level, int maxLevel) {
		if (list.isEmpty() || BTreePrinter.isAllElementsNull(list)){
			return;
		}
		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		BTreePrinter.printWhitespaces(firstSpaces);

		List<AvlNode> newNodes = new ArrayList<AvlNode>();
		for (AvlNode node : list) {
			if (node != null) {
				System.out.print(node.data);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			BTreePrinter.printWhitespaces(betweenSpaces);
		}
		System.out.println("");

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < list.size(); j++) {
				BTreePrinter.printWhitespaces(firstSpaces - i);
				if (list.get(j) == null) {
					BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}

				if (list.get(j).left != null)
					System.out.print("/");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(i + i - 1);

				if (list.get(j).right != null)
					System.out.print("\\");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printNodeInternal(newNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static int maxLevel(AvlNode node) {
		if (node == null)
			return 0;

		return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
	}

	private static <T> boolean isAllElementsNull(List<AvlNode> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}
	

}
