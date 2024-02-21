package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentRecordMemoBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum
import java.time.LocalDate

class RecordMemoFragment : Fragment() {
   lateinit var fragmentRecordMemoBinding: FragmentRecordMemoBinding
   lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentRecordMemoBinding = FragmentRecordMemoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()
        return fragmentRecordMemoBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentRecordMemoBinding.apply {
            materialToolbar7.apply {
                title = "나의 운동 메모"
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭했을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.RECORD_MEMO_FRAGMENT)
                }
            }
        }
    }
    //이벤트 설정
    @RequiresApi(Build.VERSION_CODES.O)
    fun setEvent(){
        fragmentRecordMemoBinding.apply {
            //버튼을 클릭했을 때
            memoRecordButton.setOnClickListener {
                checkOK()
            }
        }
    }
    //입력받기
    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(){
        fragmentRecordMemoBinding.apply {
            var date = LocalDate.now().toString()
            var exerciseTime = timeRecordText.text.toString().toInt()
            //이건 enum class만들고 진행
            var memo = exerciseMemoText.text.toString()

        }
    }

    //유효성 검사
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkOK(){
        fragmentRecordMemoBinding.apply {
            var exerciseTime = timeRecordText.text.toString()
            if (exerciseTime.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "운동 시간 입력 오류", "운동 시간을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(timeRecordText, mainActivity)
                }
                return
            }

        }
        getData()
        mainActivity.removeFragment(FragmentName.RECORD_MEMO_FRAGMENT)

    }
}



































