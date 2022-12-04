package algorithms;

public class TokenBucket implements RateLimiter{
    private final long maxBucketSize;
    private final long refillRate;
    private double currentBucketSize;
    private long lastRefillTimeStamp;

    public TokenBucket(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        currentBucketSize = maxBucketSize;
        lastRefillTimeStamp = System.nanoTime();
    }

    @Override
    public synchronized boolean allowRequest() {
        refill();
        if(currentBucketSize>=1)  {
            currentBucketSize-=1;
            System.out.println(" current bucket size: " + currentBucketSize);
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = (now-lastRefillTimeStamp)* refillRate/1e9;
        System.out.println("before refilled: " + currentBucketSize + " ");
        currentBucketSize = Math.min(currentBucketSize+tokensToAdd, maxBucketSize);
        System.out.print("After refill: " + currentBucketSize);
        lastRefillTimeStamp = now;
    }
}
