saga +kafka+ choriography + multi model project --> https://www.youtube.com/watch?v=aOen1-pQLZg&t=646s
													https://www.youtube.com/watch?v=6O5iJ7PKUhs [1.5 hours]
				
==>multi model project
		1) STS-> maven project -> "Create asimple project" select |default workspace Location -> Artificate- fill (packaging:pom)->
		   patent project -skip -->  complete 
				<modules>
				  <module>userSerMod</module>
			    </modules>
						
						2.1) STS-> maven project -> "Create asimple project" select | default workspace/[userSerMod] -> Artificate- fill(packaging:jar)| patent project -skip
						
						2.2) select root project -> new-> maven module-> 
						
				https://www.youtube.com/watch?v=aOen1-pQLZg&t=646s
						install kafka and | cofigure log ,port and run (zookeeper kafka)
						create title (for message quese
						publish & consium message from spring boot
						
				https://www.youtube.com/watch?v=6O5iJ7PKUhs [1.5 hours]
						programing min 11 done --> 
							order_service
							payment_service
							
							common_dtos  ---> this artiviceid+group_id addedd to user/payment service
							
							publish ---> orcerevent[ORDER_CREATED] enum [Min22]
										 Sinks.Many<OrderEvent> orderSinks. tryEmitNext(orderEvent)
						
							1) ORDER -PUBLISH [min 30 done]
									orderSupplier()
										orderSupplier-out-0: order-event
							
							2) PAYMEN - CUNSIUM/and publish stccess/fail transaction even (Function)  [min 30--58min ] 
									paymentProcessor() consume/publish
										paymentProcessor()
											paymentProcessor-in-0 :order-event  
											paymentProcessor-out-0:payment-event
											
							3) ORDER CONSUME [Min 59 -----------]
									paymentEventConsumer()
										paymentEventConsumer-in-0 : payment-event
										
										
							4) start kafka server [min 1.11]
									cd c://kafka2.1/bin/windows
									zookeeper-server-start.bat c://kafka2.1/config/zookeeper.proporty
									
									cd c://kafka2.1/bin/windows
									kafka-server-start.bat c://kafka2.1/config/server.proporty
									
									kafkatool-> download [min 1.13]
										connect
										
									run
										1) order server
										2) payment service
										3) postman
												post 
												http:local:8081/order/create
													{
														user:105
														product: aaa
														mount:500		
													}
													
										4) kafka toll
												topics are valiable now.