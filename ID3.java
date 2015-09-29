package classify_id3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Id3 {

	/**
	 * 递归打印树结构
	 * 
	 * @param root
	 *            当前待输出信息的结点
	 */
	public static void printTree(TreeNode root) {
		System.out.println("name:" + root.getName());
		ArrayList<String> rules = root.getRule();
		System.out.print("node rules: {");
		for (int i = 0; i < rules.size(); i++) {
			System.out.print(rules.get(i) + " ");
		}
		System.out.print("}");
		System.out.println("");
		ArrayList<TreeNode> children = root.getChild();
		int size = children.size();
		if (size == 0) {
			System.out.println("-->leaf node!<--");
		} else {
			System.out.println("size of children:" + children.size());
			for (int i = 0; i < children.size(); i++) {
				System.out.print("child " + (i + 1) + " of node "
						+ root.getName() + ": ");
				printTree(children.get(i));
			}
		}
	}

	/**
	 * 文件流读取训练元组
	 * 
	 * @return 训练元组集合
	 * @throws IOException
	 */
	public static ArrayList<ArrayList<String>> readFData(String fileUrl)
			throws IOException {
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(fileUrl)), "UTF-8"));
		String temp = null;
		String[] tempArray = null;
		while ((temp = in.readLine()) != null) {
			tempArray = temp.split("\\t");
			ArrayList<String> s = new ArrayList<String>();
			for (int i = 0; i < tempArray.length; i++) {
				// ///////////////

				s.add(tempArray[i]);
			}
			datas.add(s);
		}
		in.close();
		return datas;
	}

	/**
	 * 文件流读取候选属性
	 * 
	 * @return 候选属性集合
	 * @throws IOException
	 */
	public static ArrayList<String> readFCandAttr(String fileUrl)
			throws IOException {
		ArrayList<String> candAttr = new ArrayList<String>();
		String temp = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(fileUrl)), "UTF-8"));
		while ((temp = in.readLine()) != null) {
			candAttr.add(temp);
		}
		in.close();
		return candAttr;
	}

	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<String>> Datas = readFData(".//data//text.txt"); 
		// 读入数据文件，长宽不限，需用table键隔开
		ArrayList<String> features = readFCandAttr(".//data//feature.txt");
		// 读入特征文件，长宽不限，需用table键隔开
		
		printTree(DecisionTree.buildTree(Datas, features));
//		DecisionTree.buildTree(Datas, features);
	}
}
