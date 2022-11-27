package com.example.narcissus.ui.appointment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.narcissus.R;
import com.example.narcissus.databinding.FragmentAppointmentBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentFragment extends Fragment {

    private FragmentAppointmentBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    private AppointmentViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);

        binding = FragmentAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        doTypeAppointments();
        doTypeProcedures();
        doClinics();
        doDate();

        binding.submit.setOnClickListener(view -> doValidate());

        viewModel.getCreateError().observe(getActivity(), this::createError);
        viewModel.getCreateInvalid().observe(getActivity(), this::createInvalid);
        viewModel.getCreateSuccess().observe(getActivity(), this::createSuccess);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void createInvalid(Void avoid) {
        Toast.makeText(getContext(), "Selecione o dia e horário !!!", Toast.LENGTH_LONG).show();
    }

    private void createSuccess(Void avoid) {
        Toast.makeText(getContext(), "Agendamento salvo com sucesso !!!", Toast.LENGTH_LONG).show();
        binding.date.setText("");
    }

    private void doDate() {
        final DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        binding.date.setOnClickListener(view -> new DatePickerDialog(getContext(),
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show());
    }

    private void updateLabel() {
        final String format = "dd/MM/yyyy";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format, new Locale("pt", "BR"));
        binding.date.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void doValidate() {
        String typeAppointment = (String) binding.typeAppointment.getSelectedItem();
        String typeProcedure = (String) binding.typeProcedure.getSelectedItem();
        String clinic = (String) binding.clinic.getSelectedItem();
        String date = binding.date.getText().toString();

        viewModel.validate(typeAppointment, typeProcedure, clinic, date);
    }

    private void doTypeProcedures() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.procedures_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeProcedure.setAdapter(adapter);
    }

    private void doTypeAppointments() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.appointments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeAppointment.setAdapter(adapter);
    }

    private void doClinics() {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.clinics_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.clinic.setAdapter(adapter);
    }
}