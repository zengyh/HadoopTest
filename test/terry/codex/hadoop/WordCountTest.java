package terry.codex.hadoop;

import junit.framework.TestCase;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import terry.codex.hadoop.WordCount.WordCountMapper;
import terry.codex.hadoop.WordCount.WordCountReducer;

/**
 * @author yh.zeng
 * 2016-7-24
 */
public class WordCountTest extends TestCase {

	public void test1() throws Exception{
		String input = "hdfs://namenode1:9000/user/testdir/mapreduce/wordcount/input";
		String output = "hdfs://namenode1:9000/user/testdir/mapreduce/wordcount/output";

		JobConf conf = new JobConf(WordCount.class);
		conf.setJobName("WordCount");
		conf.addResource("classpath:/core-site.xml");
		conf.addResource("classpath:/hdfs-site.xml");
		conf.addResource("classpath:/mapred-site.xml");
		conf.addResource("classpath:/yarn-site.xml");

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		conf.setMapperClass(WordCountMapper.class);
		conf.setCombinerClass(WordCountReducer.class);
		conf.setReducerClass(WordCountReducer.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(input));
		FileOutputFormat.setOutputPath(conf, new Path(output));

		JobClient.runJob(conf);
		
	}
}
