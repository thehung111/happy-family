package com.happy.model;

/**
 * Created by Hung on 21/9/16.
 */
public class RestResultWrapper {

    public enum ApiStatus{
        OK, FAIL
    };

    public enum ApiError{
        INTEGER_INPUT(100, "Parameter qty is mandatory and must be an integer"),
        POSITIVE_INTEGER_INPUT(101, "Parameter qty must be a positive integer")
        ;

        public final int errCode;
        public final String errMsg;

        ApiError(int errCode, String errMsg){
            this.errCode = errCode;
            this.errMsg = errMsg;
        }
    }

    public RestResultWrapper() {
    }

    private ApiStatus status = ApiStatus.OK;
    private int err_code = -1;
    private String err_msg = "";
    private Object result;

    public void setErr(ApiError err){
        this.status = ApiStatus.FAIL;
        this.err_code = err.errCode;
        this.err_msg = err.errMsg;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
