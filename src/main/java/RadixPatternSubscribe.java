import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RadixPatternSubscribe {
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis();
            JedisPubSub jedisPubSub = new JedisPubSub() {

                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("hello from onMessage==="+channel);
                }

                @Override
                public void onPMessage(String pattern, String channel, String message) {
                    System.out.println("Channel " + channel + " has sent a message : " + message + " on pattern " + pattern);
                    if (pattern.equals("C*")) {
                        /* Unsubscribe from pattern C* after first message is received. */
                        punsubscribe(pattern);
                    }
                }

                @Override
                public void onPSubscribe(String pattern, int subscribedChannels) {
                    System.out.println("Client is Subscribed to pattern : " + pattern);
                    System.out.println("Client is Subscribed to " + subscribedChannels + " no. of patterns");
                }


                @Override
                public void onPUnsubscribe(String pattern, int subscribedChannels) {
                    System.out.println("Client is Unsubscribed from pattern : " + pattern);
                    System.out.println("Client is Subscribed to " + subscribedChannels + " no. of patterns");
                }
            };
            jedis.psubscribe(jedisPubSub, "A*", "D?");
        }catch (Exception e){
            System.out.println("Exception : " + e.getMessage());
        }finally {
            jedis.close();
        }
    }
}
