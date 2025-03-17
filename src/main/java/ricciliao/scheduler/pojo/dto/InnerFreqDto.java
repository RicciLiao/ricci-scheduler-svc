package ricciliao.scheduler.pojo.dto;

import java.io.Serializable;

public class InnerFreqDto implements Serializable {

    private static final long serialVersionUID = -2319451720505726097L;
    private String freq;

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }
}
