package com.agenda.scan;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Scan{
	
  public static void detectText() throws IOException {
    // TODO(developer): Replace these variables before running the sample.
	
	  
	
    //String filePath = "";  
    //detectText("C:/Users/Temp/scan/img4.jpg");
  }

  // Detects text in the specified image.
  public static void detectText(String filePath) throws IOException {
    List<AnnotateImageRequest> requests = new ArrayList<>();

    ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

    Image img = Image.newBuilder().setContent(imgBytes).build();
    Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
    AnnotateImageRequest request =
        AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
    requests.add(request);

    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the "close" method on the client to safely clean up any remaining background resources.
    try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
      BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
      List<AnnotateImageResponse> responses = response.getResponsesList();

      for (AnnotateImageResponse res : responses) {
        if (res.hasError()) {
          System.out.format("Error: %s%n", res.getError().getMessage());
          return;
        }
        
        StringBuilder sb = new StringBuilder();
       
        
        // For full list of available annotations, see http://g.co/cloud/vision/docs
        for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
         // System.out.format("Text: %s%n", annotation.getDescription());
         // System.out.format("Position : %s%n", annotation.getBoundingPoly());
          
          sb.append(annotation.getDescription());
        }
  
        System.out.println(PillName(sb));
      }
    }
  }
  
  public static List<String> PillName(StringBuilder s) {
	 
	  Pattern pattern = Pattern.compile("\\d{9}(.*?)[(]");
	  Matcher matcher = pattern.matcher(s);
	  List<String> Pill = new ArrayList<String>();
			  		
	  while (matcher.find()) {
			Pill.add(matcher.group(1));
			  		    		    
			if(matcher.group(1) ==  null) {
			      break;
			}
		}
	  return Pill;
	  		
  }
}