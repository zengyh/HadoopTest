package terry.codex.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

/**
 * @编写人： yh.zeng
 * @编写时间：2016-9-1 下午11:51:20
 * @文件描述: HDFS工具类
 */
public class HdfsUtils {
	
	Logger logger = Logger.getLogger(HdfsUtils.class);
	
	/**
	 * 获取FileSystem
	 * @return
	 * @throws Exception
	 */
	public static FileSystem getFileSystem() throws Exception{
		Configuration configuration = new Configuration();
		FileSystem fileSystem = FileSystem.get(configuration);
		
		return fileSystem;
	}
	
	/**
	 * 读取文件内容
	 * @param filePath 文件的路径，包含文件名
	 * @return
	 */
	public static String getFileContent(String filePath){
		try {
			getFileSystem().open(new Path(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception{
        System.out.println(getFileSystem());
	}

}
