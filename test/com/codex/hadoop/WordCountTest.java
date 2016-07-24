package com.codex.hadoop;

import java.io.IOException;

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

import com.codex.hadoop.WordCount.WordCountMapper;
import com.codex.hadoop.WordCount.WordCountReducer;

/**
 * @author yh.zeng
 * 2016-7-24
 */
public class WordCountTest extends TestCase {

	public void test1() throws IOException{
		String input = "hdfs://hadoop:9000/test-dir";
		String output = "hdfs://hadoop:9000/test-dir/result";

		JobConf conf = new JobConf(WordCount.class);
		conf.setJobName("WordCount");
		conf.addResource("classpath:/core-site.xml");
		conf.addResource("classpath:/hdfs-site.xml");
		conf.addResource("classpath:/mapred-site.xml");

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
