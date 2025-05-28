'use client';

import { createContext, useContext, useState, ReactNode } from 'react';

interface User {
  id: string;
  name: string;
  email: string;
  cpf: string;
  password: string;
  role: 1 | 2;
}

interface AuthContextType {
  user: User | null;
  login: (email: string, senha: string) => Promise<void>;
  register: (nome: string, email: string, senha: string, cpf: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);

  const login = async (email: string, senha: string) => {
    // TODO: Implement actual login logic with API
    const response = await fetch('http://localhost:3000/api/login/singin', {
      method: 'POST',
      body: JSON.stringify({ email, senha }),
    });
    const data = await response.json();

    if (response.ok) {
      setUser(data);
    } else {
      throw new Error('Login failed');
    }
  };

  const register = async (nome: string, email: string, senha: string, cpf: string) => {
    const nivel = 1;
    const response = await fetch('http://localhost:3000/api/login/singup', {
      method: 'POST',
      body: JSON.stringify({ nome, email, senha, cpf , nivel}),
    });
    const data = await response.json();

    if (response.ok) {
      setUser(data);
    } else {
      throw new Error('Registration failed');
    }
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
} 