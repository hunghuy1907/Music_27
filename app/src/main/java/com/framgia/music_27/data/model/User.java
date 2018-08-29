package com.framgia.music_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.music_27.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class User implements Parcelable {
    private int mId;
    private String mUserName;
    private String mFullName;

    protected User(Parcel in) {
        mId = in.readInt();
        mUserName = in.readString();
        mFullName = in.readString();
    }

    private User (UserBuider userBuider) {
        mId = userBuider.mId;
        mUserName = userBuider.mUserName;
        mFullName = userBuider.mFullName;
    }

    public User(JSONObject jsonObject) throws JSONException {
        mFullName = jsonObject.getString(Constants.JsonUserKey.USER_FULLNAME);
        mId = jsonObject.getInt(Constants.JsonUserKey.USER_ID);
        mUserName = jsonObject.getString(Constants.JsonUserKey.USER_NAME);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mFullName);
        dest.writeString(mUserName);
    }

    public class UserBuider {
        private int mId;
        private String mFullName;
        private String mUserName;

        public UserBuider id (int id) {
            mId = id;
            return this;
        }

        public UserBuider fullName (String fullName) {
            mFullName = fullName;
            return this;
        }

        public UserBuider userName (String userName) {
            mUserName = userName;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }
}
