package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentBottomShowBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class BottomShowFragment : BottomSheetDialogFragment() {

    lateinit var fragmentBottomShowBinding: FragmentBottomShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBottomShowBinding = FragmentBottomShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        showData()
        setEvent()
        return fragmentBottomShowBinding.root
    }

    //내용을 보여준다
    fun showData(){
        fragmentBottomShowBinding.apply {
            var userId = arguments?.getString("userId")
            if (userId != null){
                var str = MemoDAO.selectOneMemo(mainActivity, userId)

                bottomUserId.text = "아이디 : ${str?.userId}"
                bottomDateText.text = "날짜 : ${str?.dateTime}"
                bottomTImeText.text = "운동 시간 : ${str?.exerciseTime}분"
                bottomBodyText.text = when(str?.exerciseBody){
                    0 -> ExerciseBody.CHEST.str
                    1 -> ExerciseBody.BACK.str
                    2 -> ExerciseBody.SHOULDER.str
                    3 -> ExerciseBody.ARMS.str
                    4 -> ExerciseBody.LEGS.str
                    5 -> ExerciseBody.EXTRA.str
                    else -> ExerciseBody.EXTRA.str
                }
                bottomExtraText.text = "운동 메모 : ${str?.other}"
            }

        }
    }
    //이벤트 설정
    fun setEvent(){
        fragmentBottomShowBinding.apply {
            bottomModifyButton.setOnClickListener {
                var userId = arguments?.getString("userId")
                var bundle = Bundle()
                bundle.putString("userId", userId)


                mainActivity.replaceFragment(FragmentName.MEMO_MODIFY_FRAGMENT, true, true, bundle)
                dismiss()

            }
            bottomDeleteButton.setOnClickListener {
                enum.showDiaLog(mainActivity, "메모 삭제", "정말 삭제하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                    var userId = arguments?.getString("userId")
                    if (userId != null){
                        MemoDAO.deleteMemo(mainActivity, userId)
                        //Recyclerview갱신
                        mainActivity.reloadRecyclerView()
                        dismiss()

                    }
                }
            }
            bottomCancelButton.setOnClickListener {
                dismiss()
            }
        }
    }

}


















































