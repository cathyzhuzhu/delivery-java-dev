//package com.twoyum.commons.utils;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.*;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * SDK接口方法
// * Created by wangwl on 2017/7/13.
// */
//public class S3SDKClientTest {
//
//    public static void main(String[] args) {
//        getBucket("personsal_upload");
//    }
//
//    /**
//     * ##########################################
//     *   bucket的操作
//     * ##########################################
//     */
//
//    /**
//     * 获取bucket
//     * @param bucket_name
//     * @return
//     */
//    public static Bucket getBucket(String bucket_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        Bucket named_bucket = null;
//        List<Bucket> buckets = s3.listBuckets();
//        for (Bucket b : buckets) {
//            if (b.getName().equals(bucket_name)) {
//                named_bucket = b;
//            }
//        }
//        return named_bucket;
//    }
//
//    /**
//     * 创建bucket
//     * @param bucket_name
//     * @return
//     */
//    public static Bucket createBucket(String bucket_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        Bucket b = null;
//        if (s3.doesBucketExist(bucket_name)) {
//            System.out.format("Bucket %s already exists.\n", bucket_name);
//            b = getBucket(bucket_name);
//        } else {
//            try {
//                b = s3.createBucket(bucket_name);
//            } catch (AmazonS3Exception e) {
//                System.err.println(e.getErrorMessage());
//            }
//        }
//        return b;
//    }
//
//    /**
//     * 列出所有的buckets
//     * @return
//     */
//    public static List<Bucket> listBuckets() {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        List<Bucket> buckets = s3.listBuckets();
//        System.out.println("Your Amazon S3 buckets:");
//        for (Bucket b2 : buckets) {
//            System.out.println("* " + b2.getName());
//        }
//        return buckets;
//    }
//
//    /**
//     * 删除不受版本控制的存储桶之前从中删除对象.
//     * 要在删除不受版本控制的存储桶之前从中删除对象，
//     * 可以使用 AmazonS3 客户端的 listObjects 方法检索对象列表，使用 deleteObject 删除各个对象。
//     * @param bucket_name
//     */
//    public static void deleteObjectsNoVersion(String bucket_name){
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            System.out.println(" - removing objects from bucket");
//            //获取该bucket下的所有对象
//            ObjectListing object_listing = s3.listObjects(bucket_name);
//            while (true) {
//                for (Iterator<?> iterator = object_listing.getObjectSummaries().iterator(); iterator.hasNext();) {
//                    S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
//                    s3.deleteObject(bucket_name, summary.getKey());
//                }
//
//                // more object_listing to retrieve?
//                if (object_listing.isTruncated()) {
//                    object_listing = s3.listNextBatchOfObjects(object_listing);
//                } else {
//                    break;
//                }
//            };
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//
//    }
//
//    /**
//     * 删除受版本控制的存储桶之前从中删除对象
//     * 如果您使用受版本控制的存储桶，还需要先删除存储桶中存储的所有受版本控制对象，然后才能删除存储桶。
//     * 使用在删除存储桶中对象时使用的相似方法，通过使用 AmazonS3 客户端的 listVersions 方法列出所有受版本控制的对象，
//     * 然后使用 deleteVersion 删除各个对象。
//     * @param bucket_name
//     */
//    public static void deleteObjectsVersion(String bucket_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            System.out.println(" - removing objects from bucket");
//            ObjectListing object_listing = s3.listObjects(bucket_name);
//            while (true) {
//                for (Iterator<?> iterator = object_listing.getObjectSummaries().iterator(); iterator.hasNext();) {
//                    S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
//                    s3.deleteObject(bucket_name, summary.getKey());
//                }
//
//                // more object_listing to retrieve?
//                if (object_listing.isTruncated()) {
//                    object_listing = s3.listNextBatchOfObjects(object_listing);
//                } else {
//                    break;
//                }
//            };
//
//            System.out.println(" - removing versions from bucket");
//            VersionListing version_listing = s3.listVersions(
//                    new ListVersionsRequest().withBucketName(bucket_name));
//            while (true) {
//                for (Iterator<?> iterator = version_listing.getVersionSummaries().iterator(); iterator.hasNext();) {
//                    S3VersionSummary vs = (S3VersionSummary)iterator.next();
//                    s3.deleteVersion(bucket_name, vs.getKey(), vs.getVersionId());
//                }
//
//                if (version_listing.isTruncated()) {
//                    version_listing = s3.listNextBatchOfVersions(version_listing);
//                } else {
//                    break;
//                }
//            }
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * 删除一个空的bucket
//     * @param bucket_name
//     */
//    public static void deleteEmptyBucket(String bucket_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            s3.deleteBucket(bucket_name);
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//
//    }
//
//    /**
//     * ##########################################
//     *   object的操作
//     * ##########################################
//     */
//
//    /**
//     * 上传对象到bucket
//     * @param bucket_name
//     * @param file_path 文件对象路径
//     */
//    public static void putObject(String bucket_name, String file_path) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        String key_name = Paths.get(file_path).getFileName().toString();
//        try {
//            s3.putObject(bucket_name, key_name, new java.io.File(file_path));
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * 列出bucket里的所有对象。
//     * listObjects 方法返回一个 ObjectListing 对象，该对象提供有关存储桶中对象的信息。
//     * @param bucket_name
//     */
//    public static void listObjects(String bucket_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        ObjectListing ol = s3.listObjects(bucket_name);
//        //使用 getObjectSummaries 方法获取 S3ObjectSummary 对象的列表
//        List<S3ObjectSummary> objects = ol.getObjectSummaries();
//        for (S3ObjectSummary os: objects) {
//            //调用其 getKey 方法以检索对象名称
//            System.out.println("*对象名称： " + os.getKey());
//        }
//    }
//
//    /**
//     * 下载对象
//     * 使用 AmazonS3 客户端的 getObject 方法，并向其传递要下载的存储桶和对象的名称。
//     * 如果成功，此方法将返回一个 S3Object。指定的存储桶和对象键必须存在，否则将出现错误。
//     * @param bucket_name
//     * @param key_name 对象的名称
//     */
//    public static void getObject(String bucket_name, String key_name) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            S3Object o = s3.getObject(bucket_name, key_name);
//            //获取对象的内容,返回一个 S3ObjectInputStream，其行为与标准 Java InputStream 对象的相同
//            S3ObjectInputStream s3is = o.getObjectContent();
//            //定义一个对象输出的文件
//            FileOutputStream fos = new FileOutputStream(new File(key_name));
//            byte[] read_buf = new byte[1024];
//            int read_len = 0;
//            while ((read_len = s3is.read(read_buf)) > 0) {
//                fos.write(read_buf, 0, read_len);
//            }
//            s3is.close();
//            fos.close();
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        } catch (FileNotFoundException e) {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//
//    }
//
//    /**
//     * 复制对象（从一个bucket到另一个bucket）。
//     * 您可以将 copyObject 与 deleteObject 配合使用来移动或重命名对象，
//     * 方式是先将对象复制到新名称 (您可以使用与源和目标相同的存储桶)，然后从对象的旧位置删除对象。
//     * @param from_bucket
//     * @param object_key 对象的名称
//     * @param to_bucket
//     */
//    public static void copyObject(String from_bucket, String object_key, String to_bucket) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            s3.copyObject(from_bucket, object_key, to_bucket, object_key);
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * 删除对象
//     * @param bucket_name
//     * @param object_key 对象的名称
//     */
//    public static void deleteObject(String bucket_name, String object_key) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            s3.deleteObject(bucket_name, object_key);
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * 批量删除对象
//     * @param bucket_name
//     * @param object_keys 对象名称的数组
//     */
//    public static void deleteObjects(String bucket_name, String object_keys) {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//        try {
//            DeleteObjectsRequest dor = new DeleteObjectsRequest(bucket_name).withKeys(object_keys);
//            s3.deleteObjects(dor);
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }
//    }
//
//}