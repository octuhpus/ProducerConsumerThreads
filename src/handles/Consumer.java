package handles;

public class Consumer implements Runnable{

	public Thread consume;
	Handler h;
	Producer p;
	
	public Consumer (Handler h) {
		this.h = h;
		
		consume = new Thread(this);
	}
	
	public void startConsuming() {
		this.consume.start();
	}
	
	@Override
	public void run() {
		
		synchronized(h) {
		
			while(true) {
				if (h.getBuffItem(0) == 1) {
				
					//if (h.getBuffItem(MAX_BUFFER_SIZE - 1) == 0) { 
					//	h.notifyAll();
					//}
				
					h.consumeBuffAtBottom();
					//p.notifyAll();
				
					//Handler.updateBuff();
					//p.notify();
				
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();	
					}
				} else {
					//Thread.onSpinWait();
					//synchronized(h) {
					//System.out.println("yes1");
					
					
					try {
						h.hNot(h);
						System.out.println("Consumer Waiting");
						Thread.sleep(100);
						h.wait();
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("yes");
					//}
					//System.out.println("yes2");
				}
			}	
		}
	}
	
	public void setProduce(Producer p) {
		this.p = p;
	}

}
