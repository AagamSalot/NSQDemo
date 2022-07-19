import redis.clients.jedis.Jedis;
 
public class RadixChannelPublish {
 
  public static void main(String[] args) {
         
    Jedis jedis = null;
         
    try {           
        /* Creating Jedis object for connecting with redis server */
        jedis = new Jedis();

        jedis.publish("C1", "First message to channel C1");

        jedis.publish("C2", "First message to channel C2");
         

        jedis.publish("C1", "Second message to channel C1");

        jedis.publish("C2", "Second message to channel C2");
         
    } catch(Exception ex) {         
             
        System.out.println("Exception : " + ex.getMessage());
    } finally {
             
        if(jedis != null) {
          jedis.close();
        }       
    }
  }
}