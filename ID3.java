import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Id3 {

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
		ArrayList<ArrayList<String>> Datas = readFData("d://text.txt");
		ArrayList<String> features = readFCandAttr("d://feature.txt");
//		System.out.println(Datas.get(1).get(4));
		DecisionTree.buildTree(Datas, features);
	}
}
