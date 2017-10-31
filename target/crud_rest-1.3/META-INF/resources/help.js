
angular.module("HelpApp",[]);
        
        value('urlBase','http://localhost:8080/crud_rest/rest/')
        
        controller("ChamadoController",function ($http,urlBase){
        var self = this;
        
            self.usuario = 'Everton Ribeiro';
            
            self.chamados = []; 
            
            self.chamado = undefined;
            
            self.salvar = function(){
                var metodo = 'POST';
                if(self.chamado.id){
                    metodo = 'PUT';
                }
                
                $http({
                    method: metodo,
                    url: urlBase+'chamado/',
                    data:self.chamado
                }) .then (function successCallBack(response){
                  self.atualizarTabela();  
                },function errorCallBack(response){
                   self.ocorreuErro(); 
                });
            };
            
            self.novo = function (){
              self.chamado = {};  
            };
            
            self.alterar = function(chamado){
                this.chamado = chamado;
            };
            
            self.deletar = function(chamado){
                self.chamado = chamado;
                
                $http({
                    method: 'DELETE',
                    url: urlBase+'chamado/'+self.chamado.id+"/"
                }) .then (function successCallBack(response){
                  self.atualizarTabela();  
                },function errorCallBack(response){
                   self.ocorreuErro(); 
                });
                
            };
            
            self.concluir = function(){
                 $http({
                    method: 'PUT',
                    url: urlBase+'chamados/'+self.chamado.id+"/"
                }) .then (function successCallBack(response){
                    self.atualizarTabela();
                },function errorCallBack(response){
                   self.ocorreuErro(); 
                });
            };
            
            self.ocorreuErro = function (){
                alert("Erro inesperado");
            };
            
            self.atualizarTabela = function (){
                   $http({
                    method: 'GET',
                    url: urlBase+'chamados/'
                }) .then (function successCallBack(response){
                    self.chamados = response.data;
                    self.chamado = undefined;
                },function errorCallBack(response){
                   self.ocorreuErro(); 
                });
            };
            
            self.activate = function () {
                self.atualizarTabela();
            };
            self.activate();
    });