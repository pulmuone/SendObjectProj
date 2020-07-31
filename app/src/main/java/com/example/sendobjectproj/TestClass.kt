package com.example.sendobjectproj

import android.os.Parcel
import android.os.Parcelable

//안드로이에서는 Class object를 4대 컴포넌트 끼리 주고 받을 수 없다.
//그래서 데이터로 변환해서 받는쪽 컴포넌트에서 다시 복호화(?) 작업을 해야한다.
// writeToParcel 에서 데이터를 만들어 주고 복호화 createFromParcel 여기서 한다.
class TestClass() : Parcelable {
    var data10: Int = 0
    var data20: String? = null

    //생성자
    constructor(data10: Int, data20: String) : this() {
        this.data10 = data10
        this.data20 = data20
    }

    constructor(parcel: Parcel) : this() {
        this.data10 = parcel.readInt()
        this.data20 = parcel.readString()
    }

    //데이터 만들때
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(data10)
        parcel.writeString(data20)
    }

    override fun describeContents(): Int {
        return 0
    }

    //companion object는 java에서 static
    //반듯이 "CREATOR" 이 이름으로 사용해야 한다.
    companion object CREATOR : Parcelable.Creator<TestClass> {
        //객체를 복원 할 때 사용
        override fun createFromParcel(parcel: Parcel): TestClass {
//            var test = TestClass()
//            test.data10 = parcel.readInt()
//            test.data20 = parcel.readString()
//            return test
            return TestClass(parcel)
        }

        //하나 이상일때 배열에 담아서 처리 하기 때문에 이곳으로 먼저 온다.
        override fun newArray(size: Int): Array<TestClass?> {
            //return arrayOfNulls(size)
            return arrayOfNulls<TestClass>(size)
        }
    }
}