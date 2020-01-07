package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelCount;

    private ContactBusiness mContactBusiness;

    private  String mName = "";
    private  String mPhone = "";

    public MainForm() {

        setContentPane(rootPanel);
        setSize(500, 200);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();
    }

    private void setListeners() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && tableContacts.getSelectedRow() != -1) {
                    mName  = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                    mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusiness.remove(mName, mPhone);
                    loadContacts();
                    mName = "";
                    mPhone = "";
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(new JFrame(), exp.getMessage());
                }
            }
        });
    }

    private void loadContacts() {
        List<ContactEntity> contacts = mContactBusiness.getList();
        String[] columnNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity c : contacts) {
            Object[] o = new Object[2];

            o[0] = c.getName();
            o[1] = c.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);

        labelCount.setText(mContactBusiness.getContactsCountDescription());
    }
}
