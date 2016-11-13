package squaring.vitrox.icepj.Helper;

/**
 * Created by miguelgomez on 11/10/16.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

public class RestApiReceiver extends ResultReceiver {

    private Receiver mReceiver;

    public RestApiReceiver(Handler handler) {
        super(handler);
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

}