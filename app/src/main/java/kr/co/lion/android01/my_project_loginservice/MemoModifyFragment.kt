package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentMemoModifyBinding
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentModifyBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum
import java.time.LocalDate

class MemoModifyFragment : Fragment() {

    lateinit var fragmentMemoModifyBinding: FragmentMemoModifyBinding
    lateinit var mainActivity: MainActivity
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMemoModifyBinding = FragmentMemoModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()
        printData()
        return fragmentMemoModifyBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentMemoModifyBinding.apply {
            materialToolbar10.apply {
                title = "나의 메모 수정"
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭했을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MEMO_MODIFY_FRAGMENT)
                }
            }
        }

    }

    //정보 출력
    @RequiresApi(Build.VERSION_CODES.O)
    fun printData(){
        fragmentMemoModifyBinding.apply {
            var date = LocalDate.now().toString()
            timeRecordmodifyText.setText("30")
            exerciseMemomodifyText.setText("후 힘들어따")

        }

    }

    //이벤트 설정
    fun setEvent(){
        fragmentMemoModifyBinding.apply {
            memoRecordmodifyButton.setOnClickListener {
                checkOK()

            }
        }

    }

    //정보 수집
    fun getData(){
        //데베 만들고 나서

    }

    //유효성 검사
    fun checkOK(){
        fragmentMemoModifyBinding.apply {
            var exerciseTime = timeRecordmodifyText.text.toString()
            if (exerciseTime.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "운동 시간 입력 오류", "운동 시간을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(timeRecordmodifyText, mainActivity)
                }
                return
            }

        }
        //getData()
        mainActivity.removeFragment(FragmentName.MEMO_MODIFY_FRAGMENT)
    }
}


















