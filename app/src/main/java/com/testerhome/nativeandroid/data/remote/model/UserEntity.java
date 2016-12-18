package com.testerhome.nativeandroid.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bin Li on 2016/12/18.
 */
public class UserEntity implements Parcelable {
    private int id;
    private String login;
    private String name;
    private String avatar_url;

    public UserEntity() {
    }

    public UserEntity(String name, String avatar_url) {
        this.name = name;
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.login);
        dest.writeString(this.name);
        dest.writeString(this.avatar_url);
    }

    protected UserEntity(Parcel in) {
        this.id = in.readInt();
        this.login = in.readString();
        this.name = in.readString();
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
