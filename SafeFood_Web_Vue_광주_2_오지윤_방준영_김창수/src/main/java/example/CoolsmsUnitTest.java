package example;
import static org.junit.Assert.*;

import java.io.Reader;
import java.util.HashMap;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.api.SenderID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

public class CoolsmsUnitTest {
	static String api_key = "NCSOECLSYACOD9Y4";
	static String api_secret = "TNSP80ZKRLPGJE4XZH6GWBMMYGQ8JFGU";

  static Message message = new Message(api_key, api_secret);
  static GroupMessage groupMessage = new GroupMessage(api_key, api_secret);
  static Image image = new Image(api_key, api_secret);
  static SenderID senderID;
  static JSONObject result;
  static JSONArray result_array;
  static HashMap<String, String> params = new HashMap<String, String>();
  public static void main(String[] args) {
	  MessageTest();
  }
  @Test
  public static void MessageTest() {
    try {
      // send					
      params.put("to", "01063977067");
      params.put("from", "01063977067");
      params.put("type", "SMS");
      params.put("text", "문자가 보내집니다요ㅠㅠ");
      result = message.send(params);

      // balance
      result = message.balance();

      // sent
      params.clear();
      try {
        result = message.sent(params);
      } catch (Exception e) {
        result = (JSONObject) JSONValue.parse(e.getMessage());
      }

      // status
      result = message.getStatus(params);

      // cancel
    } catch (Exception e) {
    }
  }


}
