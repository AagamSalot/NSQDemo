import redis.clients.jedis.Jedis;
 
public class RadixPatternPublish {
 
  public static void main(String[] args) {
     
    Jedis jedis = null;
     
    try {           
    /* Creating Jedis object for connecting with redis server */
    jedis = new Jedis();
             
    /* Publishing message to channel C13 */
    jedis.publish("C13", "Message to channel C13");
     
    /* Publishing message to channel D2 */
    jedis.publish("D22", "Message to channel D2");
         
    /* Publishing message to channel C134 */
    jedis.publish("C134", "Message to channel C134");
             
    /* Publishing message to channel D3 */
    jedis.publish("D12", "Message to channel D3");
             
    } catch(Exception ex) {         
             
            System.out.println("Exception : " + ex.getMessage());   
             
    } finally {
             
        if(jedis != null) {
            jedis.close();
        }
             
    }
  }
     
}