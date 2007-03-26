/*
 * GoogleDialog.java
 *
 * Created on 19. Januar 2007, 10:07
 */

package pagerank.visualization;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import pagerank.algorithm.DataStructure;
import pagerank.crawler.AdvancedCrawler;

/**
 *
 * @author  thomas
 */
public class GoogleDialog extends javax.swing.JDialog
{
  
  private DataStructure _data = null;
  
  private GoogleDialog _dlg;
  
  private SwingWorker _worker;
  
  private String _searchText = "";
  
  /** Creates new form GoogleDialog */
  public GoogleDialog(java.awt.Frame parent, boolean modal)
  {
    super(parent, modal);
    initComponents();
    _dlg = this;
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents()
  {
    txtSearch = new javax.swing.JTextField();
    btSearch = new javax.swing.JButton();
    btClose = new javax.swing.JButton();
    lblStatus = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    txtMaxResult = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Google Search");

    btSearch.setFont(new java.awt.Font("Dialog", 0, 12));
    btSearch.setMnemonic('s');
    btSearch.setText("Search");
    btSearch.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        onSearch(evt);
      }
    });

    btClose.setFont(new java.awt.Font("Dialog", 0, 12));
    btClose.setMnemonic('c');
    btClose.setText("Close");
    btClose.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        onClose(evt);
      }
    });

    lblStatus.setFont(new java.awt.Font("Dialog", 0, 12));
    lblStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel1.setText("maximal number of results:");

    txtMaxResult.setText("1");
    txtMaxResult.setInputVerifier(new InputVerifier()
      {
        public boolean verify(JComponent input)
        {
          boolean correct = false;
          try
          {
            if(Integer.parseInt(txtMaxResult.getText()) > 0)
            correct = true;
          }
          catch(Exception ex)
          {
            correct = false;
          }

          if(!correct)
          {
            JOptionPane.showMessageDialog(_dlg,
              "please insert a positive integer",
              "input error",
              JOptionPane.ERROR_MESSAGE);
          }

          return correct;

        }
      });

      jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
      jLabel2.setText("* 100");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
              .addComponent(jLabel1)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(txtMaxResult, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jLabel2))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
              .addComponent(btSearch)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(btClose)))
          .addContainerGap())
      );
      layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel1)
            .addComponent(txtMaxResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2))
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
          .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(btClose))
            .addGroup(layout.createSequentialGroup()
              .addComponent(btSearch)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
          .addContainerGap())
      );
      pack();
    }// </editor-fold>//GEN-END:initComponents

  private void onSearch(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onSearch
  {//GEN-HEADEREND:event_onSearch
    final String searchText = txtSearch.getText();
    
    _searchText = searchText;
    
    final int max = Integer.parseInt(txtMaxResult.getText());
    
    lblStatus.setText("searching for \"" + searchText + "\"...");
    btClose.setEnabled(true);
    btSearch.setEnabled(false);
    
    final AdvancedCrawler crawler = new AdvancedCrawler();
    
    _worker = new SwingWorker()
    {
      
      protected Object doInBackground() throws Exception
      {
        
        _data = crawler.run(searchText, max);
        
        // close window if finished
        _dlg.setVisible(false);
        
        return null;
      }
      
    };
    
    _worker.execute();
    
  }//GEN-LAST:event_onSearch

  private void onClose(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onClose
  {//GEN-HEADEREND:event_onClose
    if(_worker != null)
    {
      _worker.cancel(true);
    }
    
    // no incomplete data
    _data = null;
    
    // close
    this.setVisible(false);
  }//GEN-LAST:event_onClose
  
  
  public DataStructure getResult()
  {
    return _data;
  }
  
  public String getSearchedText()
  {
    return _searchText;
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btClose;
  private javax.swing.JButton btSearch;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel lblStatus;
  private javax.swing.JTextField txtMaxResult;
  private javax.swing.JTextField txtSearch;
  // End of variables declaration//GEN-END:variables
  
}
